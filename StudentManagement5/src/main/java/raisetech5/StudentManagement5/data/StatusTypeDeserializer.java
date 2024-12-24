package raisetech5.StudentManagement5.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StatusTypeDeserializer extends JsonDeserializer<StatusType> {

  @Override
  public StatusType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String value = p.getText();
    return StatusType.fromString(value);
  }
}

