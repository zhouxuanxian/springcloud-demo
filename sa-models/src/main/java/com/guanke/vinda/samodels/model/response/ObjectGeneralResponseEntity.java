package com.guanke.vinda.samodels.model.response;


import lombok.Getter;

/**
 * HTTP通用响应实体类，分页数据请使用 <strong>PageableTableGeneralResponseEntity</strong> 类
 *
 * @author Nicemorning
 *
 * @see com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity
 */
@Getter
public class ObjectGeneralResponseEntity {
    private final String msg;
    private final Integer code;
    private final Object data;

    /**
     * HTTP通用响应实体默认类，分页数据请使用 <strong>PageableTableGeneralResponseEntity</strong> 类<br/>
     * 如需更改参数请使用ObjectGeneralResponseEntity.Builder()<br/>
     * 默认返回参数为{msg="ok",code=200,data=null}
     *
     * @see com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity
     */
    public ObjectGeneralResponseEntity() {
        this.msg = "ok";
        this.code = 200;
        this.data = null;
    }

    @Override
    public String toString() {
        return "Response{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    /**
     * HTTP通用响应实体类，分页数据请使用 <strong>PageableTableGeneralResponseEntity</strong> 类
     *
     * @see com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity
     */
    private ObjectGeneralResponseEntity(Builder weChatResponseEntity) {
        this.msg = weChatResponseEntity.msg;
        this.code = weChatResponseEntity.code;
        this.data = weChatResponseEntity.data;
    }

    public static final class Builder {
        private String msg;
        private Integer code;
        private Object data;

        /**
         * HTTP通用响应实体Builder，分页数据请使用 <strong>PageableTableGeneralResponseEntity.Builder()</strong>
         *
         * @see com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity
         */
        public Builder() {
            this.msg = "ok";
            this.code = 200;
            this.data = null;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public ObjectGeneralResponseEntity build(){
            return new ObjectGeneralResponseEntity(this);
        }
    }
}
