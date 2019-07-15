package com.jeecg.p3.system.enums;


public enum ActJoinNumEnum {
	
	join_num_1("1","1~500"),
	join_num_2("2","500~1000"),
	join_num_3("3","1000~2000"),
	join_num_4("4","2000~5000"),
	join_num_5("5",">5000");
	
	/**
	 * 编码
	 */
    private String code;
    
    /**
     * 描述
     */
    private String value;

    private ActJoinNumEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String toEnum(String code) {
		for(ActJoinNumEnum item : ActJoinNumEnum.values()) {
			if(item.getCode().equals(code)) {
				return item.value;
			}
		}
		return null;
	}

    public String toString() {
        return "{ code: " + code + ", value: " + value +"}";
    }
}
