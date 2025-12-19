/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axcumet.codefeedback.exception;

/**
 *
 * @author ANTONIO
 */
public class QwenApiException extends RuntimeException {

    public QwenApiException(String message) {
        super(message);
    }

    public QwenApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
