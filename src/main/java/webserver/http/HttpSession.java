package webserver.http;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HttpSession {
    private static final String SESSION_INVALIDATE_MESSAGE = "세션이 이미 무효화 되었습니다.";

    private String id;
    private ConcurrentMap<String, Object> sessionMap;
    private boolean invalidate;

    public HttpSession(String id) {
        this.id = id;
        this.sessionMap = new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setAttribute(String name, String value) {
        checkInvalidate();
        this.sessionMap.put(name, value);
    }

    public Object getAttribute(String name) {
        checkInvalidate();
        return this.sessionMap.get(name);
    }

    private void checkInvalidate() {
        if (invalidate) {
            throw new IllegalStateException(SESSION_INVALIDATE_MESSAGE);
        }
    }

    public void removeAttribute(String name) {
        this.sessionMap.remove(name);
    }

    public void invalidate() {
        this.invalidate = true;
    }

    public boolean isInvalidate() {
        return invalidate;
    }

    public int size() {
        return this.sessionMap.size();
    }
}