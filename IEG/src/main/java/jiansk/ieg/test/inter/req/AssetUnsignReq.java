package jiansk.ieg.test.inter.req;

import jiansk.ieg.other.FieldInfo;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * Created by jiansk on 17-7-6.
 */
public class AssetUnsignReq implements Serializable {

    @FieldInfo(name = "帐号", comment = "只能为实帐号")
    @NotNull(message = "帐号为空")
    @Length(max = 30, message = "帐号超长")
    private String assetNo;

    @FieldInfo(name = "帐号", comment = "通常是人民币")
    @NotNull(message = "币种为空")
    private String assetUnit;

    @FieldInfo(name = "企业代码", comment = "平台客户号")
    @Length(max = 12, message = "企业代码超长")
    private String custNo;

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public String getAssetUnit() {
        return assetUnit;
    }

    public void setAssetUnit(String assetUnit) {
        this.assetUnit = assetUnit;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
}
