package com.onlinetrademanager.DataTransferObjects;

import java.util.List;

public class ResponseViewmodel {
    private List<Object> results;
    private Long count;

    public ResponseViewmodel(List<Object> results, Long count) {
        this.results = results;
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public List<Object> getResults() {
        return results;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }
}
