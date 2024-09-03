package com.stardata.observ.model.ch;

import lombok.Data;

@Data
public class Status {

    /**
     * 趋势 系统失败和业务失败还有趋势的概念
     */
    private String orient;
    /**
     * 状态类型 例如系统失败、业务失败
     */
    private String type;
}
