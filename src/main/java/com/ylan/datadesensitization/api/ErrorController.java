package com.ylan.datadesensitization.api;

import com.ylan.datadesensitization.common.R.ApiResult;
import com.ylan.datadesensitization.common.R.BizException;
import com.ylan.datadesensitization.common.R.enums.ResultCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author by pepsi-wyl
 * @date 2023-10-30 14:18
 */

@RestController
@RequestMapping("/error")
public class ErrorController {

    // GET http://localhost:8080/error/systemError
    @GetMapping("/nullPointerExceptionError")
    public ApiResult nullPointerExceptionError(){
        ArrayList<String> list = new ArrayList<>();
        list = null;
        list.add("str");
        return ApiResult.success(list);
    }

    // GET http://localhost:8080/error/systemExceptionError
    @GetMapping("/systemExceptionError")
    public ApiResult systemExceptionError(){
        ArrayList<String> list = new ArrayList<>();
        int a =10 / 0;
        list.add("str");
        return ApiResult.success(list);
    }

    // GET http://localhost:8080/error/bizExceptionError
    @GetMapping("/bizExceptionError")
    public ApiResult bizExceptionError(){
        ArrayList<String> list = new ArrayList<>();
        if (true){
            throw new BizException(ResultCodeEnum.PARAM_ERROR);
        }
        list.add("str");
        return ApiResult.success(list);
    }

}
