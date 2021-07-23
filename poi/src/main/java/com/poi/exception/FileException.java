package com.poi.exception;

/**
 * Created by jwing on 2021/4/26.
 */
public class FileException extends BaseException {

    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
