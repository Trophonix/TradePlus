package com.trophonix.tradeplus.logging;

import com.google.gson.*;

import java.lang.reflect.Type;

class NullZeroNumberAdapter implements JsonSerializer<Number>, JsonDeserializer<Number> {

  @Override public JsonElement serialize(Number number, Type type, JsonSerializationContext context) {
    return number.doubleValue() != 0 ? new JsonPrimitive(number) : null;
  }

  @Override
  public Number deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
    if (elem == null) return 0;
    return elem.getAsJsonPrimitive().getAsNumber();
  }

}
