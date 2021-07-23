package com.poi.exception;

/**
 * Created by jwing on 2021/4/26.
 */
public class FileSizeLimitExceededException extends FileException{

    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }

}
