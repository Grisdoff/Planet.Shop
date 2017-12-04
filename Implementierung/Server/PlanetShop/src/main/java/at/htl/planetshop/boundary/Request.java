package at.htl.planetshop.boundary;

import java.io.Serializable;

public class Request implements Serializable {

    private RequestType requestType;

    public Request(RequestType type) {
        this.requestType = type;
    }

    public RequestType getRequestType() {
        return this.requestType;
    }
}
