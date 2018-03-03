寒假作业
=======


实现的功能
------
* 用户登陆注册
* 用户上传视频
* 用户添加评论
* 拉取用户所传的视频
* 视频分类
* 拉取视频下的评论
* 搜索视频


接口概述
-----
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
```


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
    "Postman-Token": "38cc5f44-1ebb-7a07-a474-c647e1580e26"
  },
  "data": {
    "username": "kuangkuang",
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


>视频上传
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
>拉取用户上传的视频列表
>>请求
```javascipt
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8080/videolist?start=0&count=10&token=dHpjqCuwdG9EbdeeUqXRVcM0JqlCoTLxmkzEg1tXNx_O5vpTxg-v9QiNFfwKUjjk3wdl_ucVBInqldR5LhYtHFVhDAST2Vyx6VDD2FwF9JEgxFKXVB23VJduAXIcyltTvXffZIY4cBLqNANIMbIj2w==",
  "method": "GET",
  "headers": {
    "Content-Type": "application/x-www-form-urlencoded",
    "Cache-Control": "no-cache",
    "Postman-Token": "36cb62bc-489d-233a-56b7-72909d0976bd"
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
{"errorcode":2,"desc":"invalid token"}
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
