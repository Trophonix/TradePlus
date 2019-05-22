package com.trophonix.tradeplus.logging;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

class PostProcessingEnabler implements TypeAdapterFactory {

  @Override public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
    return new TypeAdapter<T>() {
      @Override public void write(JsonWriter writer, T obj) throws IOException {
        delegate.write(writer, obj);
      }

      @Override public T read(JsonReader reader) throws IOException {
        T obj = delegate.read(reader);
        if (obj instanceof PostProcessor) {
          ((PostProcessor) obj).doPostProcessing();
        }
        return obj;
      }
    };
  }
}
