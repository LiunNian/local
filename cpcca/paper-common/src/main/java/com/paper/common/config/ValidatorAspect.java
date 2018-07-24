package com.paper.common.config;

import com.paper.common.model.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;


@Aspect
@Component
public class ValidatorAspect {
    
    //环绕增强
    @Around(value="execution(* com.cultural.*.controller.*.*(..)) && args(..,bindingResult)")
    public Object around(ProceedingJoinPoint pj, BindingResult bindingResult) throws Throwable{
    	 Object retVal;
         if (bindingResult.hasErrors()) {
             retVal = resultToList(bindingResult);
         } else {
             retVal = pj.proceed();
         }
         return retVal;
    }
	public Result resultToList (BindingResult result){
		List<FieldError>  err=result.getFieldErrors();
	    FieldError fe;
	    List<String> list=new ArrayList<String>();
	    for (int i = 0; i < err.size(); i++) {
	        fe=err.get(i);
	        list.add(fe.getDefaultMessage());//得到错误消息
	    }
	   
		return  new Result("出错", list, 0, false);
	}
	
}
