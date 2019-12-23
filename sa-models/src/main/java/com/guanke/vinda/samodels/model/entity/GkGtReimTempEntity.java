package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GkGtReimTempEntity extends BasePojo implements Serializable {

    private String djCheckNumberstr;

    private String djListPosition;

    private String tgCheckNumberstr;

    private String tgPosition;

    private String adultShelfNumberstr;

    private String adultVindaShelfNumberstr;

    private String adultVindaShelfPosition;

    private String checkProblem;

    private String dryShelfNumberstr;

    private String dryVindaShelfNumberstr;

    private String dryVindaShelfPosition;

    private String groundingTypestr;

    private String positionInfo;

    private String reimid;

    private String salesPredict;

    private String shelfCheckNumberstr;

    private String shelfPosition;

    private String storeeq;

    private String storeType;

    private String tamponShelfNumberstr;

    private String tamponVindaShelfNumberstr;

    private String tamponVindaShelfPosition;

    private String wetShelfNumberstr;

    private String wetVindaShelfNumberstr;

    private String wetVindaShelfPosition;

    private String coredPromotionNumber;

    private String corelessPromotionNumber;

    private String handPagePromotionNumber;

    private String softPromotionNumber;

    private String wipesPromotionNumber;

    private String applyPosId;
}