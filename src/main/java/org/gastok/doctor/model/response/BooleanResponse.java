package org.gastok.doctor.model.response;

public class BooleanResponse {
    private boolean ok;

    public BooleanResponse(boolean ok) {
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
