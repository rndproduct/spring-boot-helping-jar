package com.brother.paypal.controller;

import com.brother.paypal.payload.OrderDto;
import com.brother.paypal.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.brother.paypal.util.Constant.CANCEL_URL;
import static com.brother.paypal.util.Constant.SUCCESS_URL;

@Controller
@RequestMapping("payment-management")
public class PaymentController {
    @Value("${application.url}")
    private String applicationUrl;

    private final PaypalService paypalService;

    public PaymentController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") OrderDto orderDto) {
        try {
            Payment payment = paypalService.createPayment(orderDto.getPrice(), orderDto.getCurrency(), orderDto.getMethod(),
                    orderDto.getIntent(), orderDto.getDescription(), applicationUrl + CANCEL_URL,
                    applicationUrl + SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return "";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}
