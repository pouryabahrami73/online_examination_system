package ir.pb.online_examination_system.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OnlineExaminationErrorController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineExaminationErrorController.class);
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ExceptionHandler(BindException.class)
    public Map<String, String> handleValidationExceptions(
            /*MethodArgumentNotValidException*/BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
//        model.addAttribute("message", errors.toString());
        return errors;
    }

    /*@ExceptionHandler(value = Exception.class)
    public String
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default error-view.
        *//*ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);*//*
        return mav;
    }*/

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        String errorPage = "error";
        String pageTitle = "";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        BindException exception = (BindException) request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE);
        if (status != null){
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()){
                pageTitle = "صفحه یافت نشد!";
                errorPage = "error/404";
                LOGGER.error("Error 404");
            }else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                pageTitle = "ایراد سرور!";
                errorPage = "error/500";
                LOGGER.error("Error 500");
            }else if (statusCode == HttpStatus.FORBIDDEN.value()){
                pageTitle = "عدم دسترسی!";
                errorPage = "error/403";
                LOGGER.error("Error 403");
            }else if (statusCode == HttpStatus.BAD_REQUEST.value()){
                Map<String, String> data = handleValidationExceptions(exception);
                model.addAttribute("message", data.toString());
                pageTitle = "اطلاعات درستن وارد نشده!";
                errorPage = "error/400";
                LOGGER.error("Error 400");
            }
        }
        model.addAttribute("pageTitle", pageTitle);
        return errorPage;
    }
}
