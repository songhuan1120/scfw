package cn.tjhyyt.user.exception;

import javax.servlet.ServletException;

public class NoneTokenException extends ServletException {
    public NoneTokenException(String message) {
        super(message);
    }
}
