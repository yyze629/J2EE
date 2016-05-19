package com.yinyang.yy.gooagoo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * gson适配工具类
 * @author jiangnan
 *
 */
public class GosnAdapterUtil
{
    /**
     * json化时用于改变date类型格式
     * Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,new GosnAdapterUtil.DateTypeAdapter()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
     *
     */
    public static class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date>
    {

        private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public static void setDateFormat(String formatStr)
        {
            format = new SimpleDateFormat(formatStr);
        }

        @Override
        public JsonElement serialize(Date src, java.lang.reflect.Type arg1, JsonSerializationContext arg2)
        {
            String dateFormatAsString = format.format(src);
            return new JsonPrimitive(dateFormatAsString);
        }

        @Override
        public Date deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            if (!(json instanceof JsonPrimitive))
            {
                throw new JsonParseException("The date should be a string value");
            }
            try
            {
                Date date = format.parse(json.getAsString());
                return new Date(date.getTime());
            }
            catch (ParseException e)
            {
                throw new JsonParseException(e);
            }
        }
    }
}
