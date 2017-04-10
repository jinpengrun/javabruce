namespace java com.sankuai.sjst.erp.orderaccess.model
include "Status.thrift"

//获取token openid
struct TokenOpenidResp {
    1:required Status.Status status;
    2:required string token; // token
    3:required string openid; // openid
}
//获取用户信息
//基础信息
struct GetUserInfoParam{
    1:required string token;//
    2:required string openid;//
}

enum Sex {
  UNKONWN = 0;
  MEN = 1;
  WOMEN = 2;
}

struct WeichatUserInfo{
    1:required string openid;
    2:required string unionid;
    3:optional string headimgurl; //图像地址
    4:optional string nickname; //昵称
    5:optional string remark; // 在公众号里的 标记
    6:optional Sex sex ;// 男女
}

struct WeichatUserInfoResp {
    1:required Status.Status status;
    2:required WeichatUserInfo userinfo;
}

struct GetNewTokenResp {
    1:required Status.Status status;
    2:required string token;
}