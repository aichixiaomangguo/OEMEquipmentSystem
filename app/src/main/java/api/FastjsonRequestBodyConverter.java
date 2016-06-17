package api;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.nio.charset.Charset;

import retrofit.Converter;

/**
 * Created by zhangbinbin03 on 16/6/6.
 */
public final class FastjsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final String TAG = "Fastjson";

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");


    public FastjsonRequestBodyConverter() {
    }

    @Override
    public RequestBody convert(T value) throws IOException {

        Log.e(TAG, JSON.toJSONString(value));

        return RequestBody.create(MEDIA_TYPE, JSON.toJSONString(value).getBytes(UTF_8));

    }
}
