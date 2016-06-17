package api;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by zhangbinbin03 on 16/6/6.
 */
public final class FastjsonConverterFactory extends Converter.Factory {

    public static FastjsonConverterFactory create() {
        return new FastjsonConverterFactory();
    }

    private FastjsonConverterFactory() {}

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new FastjsonResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new FastjsonRequestBodyConverter<>();
    }
}
