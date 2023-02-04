# 通过id

> URL: localhost:80/employee/1
>
> Origin Url: localhost:80/employee/{id}
>
> Type: GET


### Request headers

|Header Name| Header Value|
|---------|------|

### Parameters

##### Path parameters

| Parameter | Type | Value | Description |
|---------|------|------|------------|
|id|Number|1|eid|


##### URL parameters

|Required| Parameter | Type | Value | Description |
|---------|---------|------|------|------------|


##### Body parameters

###### JSON

```

```

###### JSON document

```

```


##### Form URL-Encoded
|Required| Parameter | Type | Value | Description |
|---------|---------|------|------|------------|


##### Multipart
|Required | Parameter | Type | Value | Description |
|---------|---------|------|------|------------|


### Response

##### Response example

```

```

##### Response document
```
{
	"code":"No comment,Type =Number",
	"msg":"No comment,Type =String",
	"data":{
		"id":"主键",
		"name":"姓名",
		"username":"用户名",
		"password":"密码",
		"phone":"手机号",
		"sex":"性别",
		"idNumber":"身份证号",
		"status":"状态 0:禁用，1:正常",
		"createTime":"创建时间",
		"updateTime":"更新时间",
		"createUser":"创建人",
		"updateUser":"修改人"
	}
}
```


