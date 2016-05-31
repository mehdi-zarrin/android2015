package com.example.mehdi.android2015.core;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;

public abstract class ServiceResponse {
    private final static String Tag = "serviceResponse";
    private String operationError;
    private HashMap<String, String> propertyErrors;
    private boolean isCritical;

    public ServiceResponse() {
        this.propertyErrors = new HashMap<>();
    }

    public ServiceResponse(HashMap<String, String> propertyErrors, boolean isCritical) {
        this.propertyErrors = propertyErrors;
        this.isCritical = isCritical;
    }

    public ServiceResponse(String operationError) {
        this.operationError = operationError;
    }

    public String getOperationError() {
        return operationError;
    }

    public void setOperationError(String operationError) {
        this.operationError = operationError;
    }

    public String getPropertyError(String property) {
        return propertyErrors.get(property);
    }

    public void setPropertyErrors(String property, String error) {
        this.propertyErrors.put(property, error);
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }


    public boolean didSucceed() {
        return (operationError == null || operationError.isEmpty()) && (propertyErrors.size() == 0);
    }

    public void showErrorToast(Context context) {
        if(context == null || operationError == null || operationError.isEmpty())
            return;

        Toast.makeText(context, operationError, Toast.LENGTH_LONG).show();
    }



}
