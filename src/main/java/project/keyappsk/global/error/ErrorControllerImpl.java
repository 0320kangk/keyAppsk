package project.keyappsk.global.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ErrorControllerImpl implements ErrorController{
    @RequestMapping("/error")
    public String handlerError(HttpServletRequest request, Model model) {
        Object errorCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        /*Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String errorMessageFromException = throwable.getMessage();
        log.info("errorMessageFromException: {}", errorMessageFromException);*/

        if(errorCode != null) model.addAttribute("errorCode", errorCode.toString());
//        if(errorMessage != null) model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

}
