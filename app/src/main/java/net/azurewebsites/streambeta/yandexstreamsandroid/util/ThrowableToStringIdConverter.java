package net.azurewebsites.streambeta.yandexstreamsandroid.util;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;

import java.io.IOException;

import retrofit2.HttpException;

/**
 * Created by Tetawex on 23.07.2017.
 */

public class ThrowableToStringIdConverter {
    public static int convert(Throwable throwable){
        if (throwable instanceof HttpException) {
            return R.string.err_server_fail;
        }
        else if (throwable instanceof IOException) {
           return R.string.err_network_fail;
        }
        else
            return R.string.err_unknown;
    }
}
