package ys.fileman.prototype.api.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonRootName(value = "response")
public class ResponseDTO<E> {

    private final Status status;
    private final Request request;
    private E data;
    @Setter(AccessLevel.NONE)
    private List<Message> messages;

    @Data
    private final static class Status {
        private final String code;

        private Status(String code) {
            this.code = code;
        }
    }

    @Data
    private final static class Request {
        private final String id;
        private final String method;
        private final String uri;
        private final String datetime;

        private Request(String id, String method, String uri, String datetime) {
            this.id = id;
            this.method = method;
            this.uri = uri;
            this.datetime = datetime;
        }
    }

    @Data
    private final static class Message {
        private final String type;
        private final String code;
        private final String message;

        private Message(String type, String code, String message) {
            this.type = type;
            this.code = code;
            this.message = message;
        }
    }

    public ResponseDTO(String status, String id, String method, String uri, String datetime) {
        this.status = new Status(status);
        this.request = new Request(id, method, uri, datetime);
    }

    public void addMessage(String type, String code, String message) {
        if (messages == null) {
            messages = new ArrayList<>();
        }

        messages.add(new Message(type, code, message));
    }
}
