寒假作业
=======


实现的功能
------
* 用户登陆注册
* 用户完整上传视频
* 用户单独上传视频
* 用户上传视频封面
* 用户修改视频信息
* 用户上传/修改头像
* 用户添加评论
* 拉取用户所传的视频
* 视频分类
* 拉取视频下的评论
* 搜索视频


接口概述
-----
>注册
>>请求
```javascript
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/register",
  "method": "POST",
  "headers": {
    "Content-Type": "application/x-www-form-urlencoded",
    "Cache-Control": "no-cache",
    "Postman-Token": "424f648f-1826-c990-5efe-6fb76dd9f0a0"
  },
  "data": {
    "username": "linjunkuang",
    "pswd": "123456"
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```
>>返回
>>注册成功
```json
{
    "errorcode": 0,
    "desc": "success"
}
```
>>错误提示符
```json
{
    "errorcode": 2,
    "desc": "user exists"
}
{
    "errorcode": 1,
    "desc": "database error"
}
```


>登陆
>>请求
```javascript
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/login",
  "method": "POST",
  "headers": {
    "Content-Type": "application/x-www-form-urlencoded",
    "Cache-Control": "no-cache",
    "Postman-Token": "597d4dbf-3486-f84b-bffa-0943e16e9b32"
  },
  "data": {
    "username": "kuang",
    "pswd": "123456"
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```
>>>返回
>>>>登陆成功
```json
{
    "promptcode": 0,
    "desc": "login success",
    "token": "dHpjqCuwdG9EbdeeUqXRVcM0JqlCoTLxmkzEg1tXNx_O5vpTxg-v9QiNFfwKUjjk3wdl_ucVBInqldR5LhYtHFVhDAST2Vyx6VDD2FwF9JEgxFKXVB23VJduAXIcyltTVETWpI78ljOQqmKocxatNw=="
}
```
>>>>失败提示符
```json
{
    "promptcode": 2,
    "desc": "account or key is error"
}
{
    "promptcode": 1,
    "desc": "database error"
}
{
    "errorcode": 1,
    "desc": "parameter error"
}
```


>视频上传附带视频信息
>>请求
```javascipt
var form = new FormData();
form.append("vedi", "C:\\Users\\ugly\\AppData\\Roaming\\Postman\\IndexedDB\\file__0.indexeddb.blob\\1\\0a\\a08");

var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://kuangjunlin.s1.natapp.cc/upmedia?title=test&sort=test2&token=dHpjqCuwdG9EbdeeUqXRVcM0JqlCoTLxmkzEg1tXNx_O5vpTxg-v9QiNFfwKUjjk3wdl_ucVBInqldR5LhYtHFVhDAST2Vyx6VDD2FwF9JEgxFKXVB23VJduAXIcyltTrUuT4WxFZuafCh5aJy4vow==",
  "method": "POST",
  "headers": {
    "Content-Type": "application/x-www-form-urlencoded",
    "Cache-Control": "no-cache",
    "Postman-Token": "ffd567c7-39a2-b9c7-cea2-7ff8aa7bffca"
  },
  "processData": false,
  "contentType": false,
  "mimeType": "multipart/form-data",
  "data": form
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```

>>返回
>>>上传成功
```json
{
    "errorcode": 0,
    "desc": "upload success"
}
```
>>>失败提示符
```json
{
    "errorcode": 1,
    "desc": "database error"
}
{
    "errorcode": 5,
    "desc": "video store error"
}
{
    "errorcode": 3,
    "desc": "invalid token"
}
```
>单独上传视频
>>请求
```javascript
var form = new FormData();
form.append("video", "C:\\Users\\ugly\\Videos\\Captures\\sample.flv");

var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/upvideo",
  "method": "POST",
  "headers": {
    "Cache-Control": "no-cache",
    "Postman-Token": "6218d04a-3ff3-5f8f-60ef-e93351576d9f"
  },
  "processData": false,
  "contentType": false,
  "mimeType": "multipart/form-data",
  "data": form
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```
>>返回
```json
{
    "errorcode": 0,
    "desc": "upload success",
    "videoid": {
        "id": 41
    }
}
{
    "errorcode": 1,
    "desc": "parameter error"
}
{
    "errorcode": 2,
    "desc": "insert datebase fail"
}

>上传视频封面
>>请求
```javascript
var form = new FormData();
form.append("pic", "C:\\Users\\ugly\\Pictures\\二维码.jpg");

var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/videocover?videoid=42",
  "method": "POST",
  "headers": {
    "Cache-Control": "no-cache",
    "Postman-Token": "56f29351-dafb-f2bc-7897-c86239079516"
  },
  "processData": false,
  "contentType": false,
  "mimeType": "multipart/form-data",
  "data": form
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```
>>返回
```json
{
    "errorcode": 0,
    "desc": "add success"
}
{
    "errorcode": 2,
    "desc": "insert database eorror"
}
```

>上传/修改用户信息
>>请求
```javascript
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/videoinfo",
  "method": "POST",
  "headers": {
    "Content-Type": "application/x-www-form-urlencoded",
    "Cache-Control": "no-cache",
    "Postman-Token": "27099ec8-9077-f55a-4b62-2700147ebc69"
  },
  "data": {
    "id": "42",
    "title": "overtest",
    "sort": "carton",
    "describe": "test",
    "token": "dHpjqCuwdG9EbdeeUqXRVcM0JqlCoTLxmkzEg1tXNx_O5vpTxg-v9QiNFfwKUjjk3wdl_ucVBInqldR5LhYtHFVhDAST2Vyx6VDD2FwF9JEgxFKXVB23VJduAXIcyltT0IPUEiP1jXsdd3kcxumuIdpU0gRDKe7fpGlWzG1qYKc="
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```
>>返回
```json
{
    "errorcode": 0,
    "desc": "success"
}
{
    "errorcode": 2,
    "desc": "datebase error"
}
{
    "errorcode": 3,
    "desc": "token invalid"
}
{
    "errorcode": 4,
    "desc": "token expired"
}
```


>拉取用户上传的视频列表
>>请求
```javascipt
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/videolist?start=0&count=10&token=dHpjqCuwdG9EbdeeUqXRVcM0JqlCoTLxmkzEg1tXNx_O5vpTxg-v9QiNFfwKUjjk3wdl_ucVBInqldR5LhYtHFVhDAST2Vyx6VDD2FwF9JEgxFKXVB23VJduAXIcyltT72wftw4ItxSdL6ulnq9236OqHLJfLSzKD7PZlEfeG5U=",
  "method": "GET",
  "headers": {
    "Cache-Control": "no-cache",
    "Postman-Token": "662a0fe2-48ef-0909-977a-466f97492822"
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```
>>返回
>>>成功
```json
{
    "errorcode": 0,
    "desc": "success",
    "video": [
        {
            "src": "videos/20180228001635 818904cf-7dbe-4886-8e50-beb9608bdafa sample.flv",
            "id": 15,
            "title": "test",
            "descript": "",
            "picture": "",
            "uptime": "Feb 28, 2018 8:16:35 AM"
        },
        {
            "src": "videos/20180228160015 3c5cba34-9f13-41a4-95e2-a31a5d14dce4 sample.flv",
            "id": 17,
            "title": "test",
            "descript": "",
            "picture": "",
            "uptime": "Mar 1, 2018 12:00:15 AM"
        },
        {
            "src": "videos/20180303192430 4fba5e46-1822-4f56-83d8-57d2733d0434 sample.flv",
            "id": 18,
            "title": "test",
            "descript": "",
            "picture": "",
            "uptime": "Mar 4, 2018 3:24:30 AM"
        }
    ]
}
```
>>>失败提示符
```json
{
    "errorcode": 1,
    "desc": "invalid parameter"
}
{
    "errorcode": 2,
    "desc": "token invalid"
}
{
    "errorcode": 2,
    "desc": "token ixpired"
}
```

>获取当前用户上传视频总数
>>请求
```javascript
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/videocount?token=dHpjqCuwdG9EbdeeUqXRVcM0JqlCoTLxmkzEg1tXNx_O5vpTxg-v9QiNFfwKUjjk3wdl_ucVBInqldR5LhYtHFVhDAST2Vyx6VDD2FwF9JEgxFKXVB23VJduAXIcyltTBh9uwpXspZn3GwUiVi6BnA==",
  "method": "GET",
  "headers": {
    "Content-Type": "application/x-www-form-urlencoded",
    "Cache-Control": "no-cache",
    "Postman-Token": "71f5d922-40c7-572a-1110-81eaec14c237"
  },
  "data": {
    "username": "kuang",
    "pswd": "123456"
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```
>>返回
```json
{
    "errorcode": 0,
    "desc": "success",
    "count": 3
}
{
    "errorcode": 3,
    "desc": "invalid token"
}

{
    "errorcode": 2,
    "desc": "token expired"
}
```
>用户添加评论
>>请求
```javascript
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/addcomment?videoid=13&token=dHpjqCuwdG9EbdeeUqXRVcM0JqlCoTLxmkzEg1tXNx_O5vpTxg-v9QiNFfwKUjjk3wdl_ucVBInqldR5LhYtHFVhDAST2Vyx6VDD2FwF9JEgxFKXVB23VJduAXIcyltTTdW5axk8SIuqt8xpbeGxpw==",
  "method": "POST",
  "headers": {
    "Cache-Control": "no-cache",
    "Postman-Token": "795ea8e1-d9fa-51d5-d3c6-6ca0924b81b5"
  },
  "data": "23333"
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```
>>返回
```json
{
    "errorcode": 0,
    "desc": "comment success"
}
{
    "errorcode": 3,
    "desc": "token invalid"
}
{
    "errorcode": 2,
    "desc": "token expired"
}
```
>拉取视频评论列表
>>请求
```javascript
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/commentlist?videoid=13&start=0&count=10",
  "method": "GET",
  "headers": {
    "Cache-Control": "no-cache",
    "Postman-Token": "52604c95-76fb-dc6f-5524-6d0ecfa95ed3"
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```
>>返回
```json
{
    "errorcode": 0,
    "desc": "suceess",
    "comment": [
        {
            "content": "23333",
            "username": "kuang"
        },
        {
            "content": "hhhh",
            "username": "kuang"
        },
        {
            "content": "66666",
            "username": "kuang"
        },
        {
            "content": "99999",
            "username": "kuang"
        },
        {
            "content": "99999",
            "username": "kuang"
        }
    ]
}
```

>视频搜索
>>请求
```javascript
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/searchvideo?videoname=tes&start=0&count=10",
  "method": "GET",
  "headers": {
    "Cache-Control": "no-cache",
    "Postman-Token": "717a72cc-d9ae-e7f6-86b8-6d6524e22797"
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```

>>返回
```json
{
    "errorcode": 0,
    "desc": "success",
    "video": [
        {
            "src": "videos/20180227202643 71b30aa9-bcce-439f-9dc9-468b458d244c sample.flv",
            "id": 13,
            "sort": "test",
            "title": "test",
            "descript": "test",
            "picture": "",
            "uptime": "Feb 28, 2018 4:26:47 AM",
            "username": "kuangjunlin"
        },
        {
            "src": "videos/20180228001635 818904cf-7dbe-4886-8e50-beb9608bdafa sample.flv",
            "id": 15,
            "sort": "test",
            "title": "test",
            "descript": "",
            "picture": "",
            "uptime": "Feb 28, 2018 8:16:35 AM",
            "username": "kuang"
        },
        {
            "src": "videos/20180303192430 4fba5e46-1822-4f56-83d8-57d2733d0434 sample.flv",
            "id": 18,
            "sort": "test2",
            "title": "test",
            "descript": "",
            "picture": "",
            "uptime": "Mar 4, 2018 3:24:30 AM",
            "username": "kuang"
        },
        {
            "src": "videos/20180307080301 5c1bb49e-e72e-4404-8258-89cfb10a90db sample.flv",
            "id": 20,
            "sort": "",
            "title": "tes",
            "descript": "tes",
            "picture": "",
            "uptime": "Mar 7, 2018 4:03:01 PM",
            "username": ""
        },
        {
            "src": "videos/20180308102145 92befbe2-8b6e-4594-a341-4be789f3ef63 sample.flv",
            "id": 37,
            "sort": "test2",
            "title": "test",
            "descript": "",
            "picture": "",
            "uptime": "Mar 8, 2018 6:33:05 PM",
            "username": "kuang"
        },
        {
            "src": "videos/20180308103342 299e35cc-1032-40cd-b062-3d50683d96e9 sample.flv",
            "id": 38,
            "sort": "newtest",
            "title": "newtest",
            "descript": "newtest",
            "picture": "",
            "uptime": "Mar 8, 2018 6:33:54 PM",
            "username": ""
        },
        {
            "src": "videos/20180308103510 887e8dff-837c-4043-a322-e82258bb9087 sample.flv",
            "id": 39,
            "sort": "carton",
            "title": "newtest",
            "descript": "test",
            "picture": "cover/20180308204909 0fa3eff7-7add-4785-ac0d-4f8edb509a41 \ufffd\ufffdά\ufffd\ufffd.jpg",
            "uptime": "Mar 8, 2018 6:35:11 PM",
            "username": ""
        },
        {
            "src": "videos/20180308205415 67a05b04-cd7a-4663-8b45-6dfe09b1c1d3 sample.flv",
            "id": 40,
            "sort": "carton",
            "title": "overtest",
            "descript": "test",
            "picture": "cover/20180308205438 8fbf6133-70ed-4d24-9cb4-bda3568506f4 \ufffd\ufffdά\ufffd\ufffd.jpg",
            "uptime": "Mar 9, 2018 4:54:15 AM",
            "username": "kuang"
        },
        {
            "src": "videos/20180309084559 5729aca0-4c78-4118-ad37-727365afbb6b sample.flv",
            "id": 42,
            "sort": "carton",
            "title": "overtest",
            "descript": "test",
            "picture": "cover/20180309085222 024394a9-66a8-42b3-9989-18cde8b72216 \ufffd\ufffdά\ufffd\ufffd.jpg",
            "uptime": "Mar 9, 2018 4:46:01 PM",
            "username": "linjunkuang"
        }
    ]
}
```

>>错误提示
```json
{
    "errorcode": 1,
    "desc": "invalid parameter"
}
```

>>视频分类
>>请求
```javascript
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/sortvideo?sort=test&start=0&count=10",
  "method": "GET",
  "headers": {
    "Cache-Control": "no-cache",
    "Postman-Token": "aced016d-b43e-0aa0-5178-501764eabeeb"
  },
  "data": {
    "sort": "test",
    "start": "0",
    "count": "10"
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```

>>返回
```json
{
    "errorcode": 0,
    "desc": "success",
    "video": [
        {
            "src": "videos/20180227202643 71b30aa9-bcce-439f-9dc9-468b458d244c sample.flv",
            "id": 13,
            "title": "test",
            "descript": "test",
            "uptime": "Feb 28, 2018 4:26:47 AM",
            "username": "kuangjunlin"
        },
        {
            "src": "videos/20180228001635 818904cf-7dbe-4886-8e50-beb9608bdafa sample.flv",
            "id": 15,
            "title": "test",
            "descript": "",
            "uptime": "Feb 28, 2018 8:16:35 AM",
            "username": "kuang"
        },
        {
            "src": "videos/20180228005721 51c9914e-f6f1-4924-b8f5-34541ec36c2a sample.flv",
            "id": 16,
            "title": "xxx",
            "descript": "",
            "uptime": "Feb 28, 2018 8:57:21 AM",
            "username": "kuangjunlin"
        }
    ]
}
```
>>错误提示
```json
{
    "errorcode": 1,
    "desc": "parameter error"
}
```

>删除视频
>>请求值
```javascript
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/deletemedia?id=42&token=dHpjqCuwdG9EbdeeUqXRVcM0JqlCoTLxmkzEg1tXNx_O5vpTxg-v9QiNFfwKUjjk3wdl_ucVBInqldR5LhYtHFVhDAST2Vyx6VDD2FwF9JEgxFKXVB23VJduAXIcyltTXZyZ9U2qE8lMEGqd7_eB8mfL2-aS7cyuucTRLLAzocU=",
  "method": "DELETE",
  "headers": {
    "Cache-Control": "no-cache",
    "Postman-Token": "70942824-0d75-9814-df3e-f971ea2b6f6b"
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```

>>返回
```json
{
    "errorcode": 0,
    "desc": "delete file success"
}
{
    "errorcode": 2,
    "desc": "delete datebase fail"
}
```




