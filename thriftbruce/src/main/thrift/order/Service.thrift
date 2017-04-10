namespace java com.sankuai.sjst.erp.orderaccess.service
include 'Model.thrift'
service OrderAccessThriftService{

     //根据code 获取token
     Model.TokenOpenidResp getTokenOpenid(1:required string code);


     Model.WeichatUserInfoResp getWeichatUserInfo(1:required Model.GetUserInfoParam userInfoParam);

     Model.GetNewTokenResp getNewToken(1:required string token);
    }