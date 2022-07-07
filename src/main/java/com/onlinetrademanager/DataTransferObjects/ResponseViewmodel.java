package com.onlinetrademanager.DataTransferObjects;

import com.onlinetrademanager.DataTransferObjects.Items.ItemList;

import java.util.List;

public class ResponseViewmodel {
    private List<ItemList> results;
    private Long count;

    public ResponseViewmodel(List<ItemList> results, Long count) {
        this.results = results;
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public List<ItemList> getResults() {
        return results;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setResults(List<ItemList> results) {
        this.results = results;
    }
}
