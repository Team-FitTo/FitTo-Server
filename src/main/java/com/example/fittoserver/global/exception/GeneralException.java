package com.example.fittoserver.global.exception;

import com.example.fittoserver.global.common.api.BaseCode;
import com.example.fittoserver.global.common.api.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseCode code;

    public ResponseDTO getErrorReason() {
        return this.code.getReason();
    }

    public ResponseDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}