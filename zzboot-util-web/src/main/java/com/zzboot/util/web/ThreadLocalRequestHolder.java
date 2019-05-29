package com.zzboot.util.web;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
public class ThreadLocalRequestHolder {

    private static final ThreadLocal<HttpServletRequest> requests = new ThreadLocal<HttpServletRequest>() {

                    @Override
                    public HttpServletRequest initialValue(){
                        return null;
                    }
        	};

    public static HttpServletRequest getRequests() {
        return requests.get();
    }
    public static void setRequests(HttpServletRequest request) {
        requests.set(request);
    }
}
