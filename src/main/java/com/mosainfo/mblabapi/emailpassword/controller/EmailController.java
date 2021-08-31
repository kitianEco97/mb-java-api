package com.mosainfo.mblabapi.emailpassword.controller;

import com.mosainfo.mblabapi.dto.Mensaje;
import com.mosainfo.mblabapi.emailpassword.dto.EmailValuesDTO;
import com.mosainfo.mblabapi.emailpassword.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/email-password")
@CrossOrigin
public class EmailController {

    @Autowired
    EmailService emailService;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto) {
        dto.setMailFrom(mailFrom);
        dto.setSubject("Cambio de contraseña");
        dto.setUserName("Kitian");
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setTokenPassword(tokenPassword);
        emailService.sendEmail(dto);
        return new ResponseEntity(new Mensaje("Te hemos enviado un correo"), HttpStatus.OK);
    }
}
