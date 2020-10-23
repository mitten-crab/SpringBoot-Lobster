/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.lobster.common.exception;

import com.lobster.common.entity.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 异常处理
 *
 * @author Administrator
 * @date 2020-10-23 16:23:34
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseResult> handleException(Throwable e) {
        log.error(e.getMessage());
        return buildResponseEntity(ResponseResult.error(e.getMessage()));
    }

    /**
     * 接口数据验证
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseResult> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
//        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
//        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
//        String msg = "不能为空";
//        if (msg.equals(message)) {
//            message = str[1] + ":" + message;
//        }
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return buildResponseEntity(ResponseResult.error(message));
    }

    private ResponseEntity<ResponseResult> buildResponseEntity(ResponseResult responseResult) {
        return new ResponseEntity<ResponseResult>(responseResult, HttpStatus.valueOf((int) responseResult.get("code")));
    }

}
