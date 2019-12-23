-- auto-generated definition
create table sa_user_info
(
    id            varchar(50)                         not null
        primary key,
    create_time   timestamp default CURRENT_TIMESTAMP not null,
    update_time   timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
    row_version   int       default 0                 not null comment '行版本号，默认为0',
    delete_flag   int       default 0                 not null comment '逻辑删除标记，默认为0，删除为1',
    phone         varchar(50)                         null comment '手机号',
    name          varchar(50)                         null comment '用户姓名',
    register_time timestamp                           null comment '用户注册时间（首次登陆时间）',
    open_id       varchar(100)                        null comment '用户openId',
    union_id      varchar(100)                        null comment '用户unionId',
    status        int       default 0                 null comment '用户状态，有效为0，失效为1',
    avatar        varchar(200)                        null comment '用户头像地址',
    row_id        varchar(50)                         null comment '用户CRM人员表中对应的RowId',
    position_id   varchar(50)                         null comment '对应CRM的职位ID',
    emp_id        varchar(50)                         null comment '用户在CRM中的EMPID'
)
    comment '经销宝用户信息表';

-- auto-generated definition
create table sa_user_time_recode
(
    id                  varchar(50)                         not null
        primary key,
    create_time         timestamp default CURRENT_TIMESTAMP not null,
    update_time         timestamp default CURRENT_TIMESTAMP not null,
    row_version         int       default 0                 not null,
    delete_flag         int       default 0                 not null,
    position_id         varchar(50)                         null comment '用户职位ID',
    phone               varchar(50)                         null comment '用户手机号',
    row_id              varchar(50)                         null comment '用户在CRM中所对应的RowId',
    last_login          timestamp                           null comment '用户上一次登陆时间',
    daily_message_check timestamp default CURRENT_TIMESTAMP not null comment '用户上一次查看工作日报中的消息时间'
)
    comment '用户相关时间节点记录表';

-- auto-generated definition
create table sa_wechat_access_token
(
    id           varchar(50)                         not null
        primary key,
    create_time  timestamp default CURRENT_TIMESTAMP not null,
    update_time  timestamp default CURRENT_TIMESTAMP not null,
    row_version  int       default 0                 not null,
    delete_flag  int       default 0                 not null,
    access_token varchar(200)                        not null,
    expires      int                                 not null
)
    comment '微信accessToken存储表';

-- auto-generated definition
create table sa_photo_conncet
(
    id                 varchar(50)                         not null
        primary key,
    create_time        timestamp default CURRENT_TIMESTAMP not null,
    update_time        timestamp default CURRENT_TIMESTAMP not null,
    row_version        int       default 0                 not null comment '行版本号，默认为0',
    delete_flag        int       default 0                 not null comment '逻辑删除标记，默认为0，删除为1',
    foreign_id         varchar(50)                         not null comment '关联外表ID',
    photo_id           varchar(50)                         not null comment '关联的图片表ID',
    photo_type         varchar(50)                         null comment '图片类型',
    create_emp_id      varchar(50)                         null comment '创建者的员工ID',
    create_position_id varchar(50)                         null comment '创建人的职位ID'
)
    comment '图片存储连接表';

-- auto-generated definition
create table sa_wx_message
(
    saWxMessageId varchar(40)                        not null comment '经销宝推送消息表Id'
        primary key,
    createdBy     varchar(40)                        not null comment '创建用户',
    createdTime   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updatedBy     varchar(40)                        not null comment '最后修改用户',
    updatedTime   datetime default CURRENT_TIMESTAMP not null comment '最后修改时间',
    status        int(1)   default 0                 not null comment '消息状态：0->等待发送；1->发送成功；2->发送失败',
    version       int      default 0                 not null comment '版本号',
    recordId      varchar(40)                        not null comment '记录Id',
    tableName     varchar(40)                        not null comment '表名',
    sendTime      datetime                           null comment '发送时间',
    agentId       int                                not null comment '企业应用Id',
    msgType       varchar(15)                        not null comment '微信消息类型：text->文本消息；textcard->文本卡片消息息；image->图片消息',
    toUser        varchar(1000)                      null comment '成员Id列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送',
    toParty       varchar(500)                       null comment '部门Id列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数',
    toTag         varchar(500)                       null comment '标签Id列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数',
    errMsg        varchar(1000)                      null comment '消息发送失败原因',
    content       longtext                           null comment '文本消息->内容',
    title         varchar(120)                       null comment '文本卡片信息->标题',
    description   varchar(510)                       null comment '文本卡片信息->描述',
    url           varchar(255)                       null comment '文本卡片信息->跳转Url',
    btnTxt        varchar(40)                        null comment '文本卡片信息->按钮文字。 默认为“详情”， 不超过4个文字，超过自动截断。',
    mediaId       varchar(500)                       null comment '图片消息->图片媒体文件Id，可以调用上传临时素材或者永久素材接口获取,永久素材media_Id必须由发消息的应用创建'
)
    comment '经销宝推送消息表';

create index sa_wx_message_n1
    on sa_wx_message (status);

-- auto-generated definition
create table sa_visit_content
(
    id                varchar(50)                         not null
        primary key,
    create_time       timestamp default CURRENT_TIMESTAMP not null,
    update_time       timestamp default CURRENT_TIMESTAMP not null,
    row_version       int       default 0                 not null,
    delete_flag       int       default 0                 not null,
    gk_visit_id       varchar(50)                         not null comment '关联GK_VISIT表的ID',
    shelf_content     varchar(500)                        null comment '货架描述',
    stack_content     varchar(500)                        null comment '地堆描述',
    end_frame_content varchar(500)                        null comment '端架描述'
)
    comment '经销宝拜访记录描述记录表';

-- auto-generated definition
create table sa_visit_ext
(
    id                 varchar(50)                         not null
        primary key,
    create_time        timestamp default CURRENT_TIMESTAMP not null,
    update_time        timestamp default CURRENT_TIMESTAMP not null,
    row_version        int       default 0                 not null,
    delete_flag        int       default 0                 not null,
    parent_id          varchar(50)                         not null comment '父表ID',
    shelf_location     varchar(100)                        null comment '货架位置',
    stack_location     varchar(100)                        null comment '堆码位置',
    end_frame_location varchar(100)                        null comment '端架位置',
    soft_count         int                                 null,
    non_core_count     int                                 null,
    had_core_count     int                                 null,
    paper_count        int                                 null,
    wet_count          int                                 null,
    expand_content     varchar(500)                        null comment '推广活动描述'
)
    comment '拜访记录扩展表';

-- auto-generated definition
create table sa_competitor_template
(
    id            varchar(50)                         not null
        primary key,
    create_time   timestamp default CURRENT_TIMESTAMP not null,
    update_time   timestamp default CURRENT_TIMESTAMP not null,
    row_version   int       default 0                 not null,
    delete_flag   int       default 0                 not null,
    visit_id      varchar(50)                         null comment '关联的拜访ID',
    competitor_id varchar(50)                         null comment '竞品ID'
)
    comment '竞品信息中间状态表';



-- auto-generated definition
create table sa_competitor_photo_temp
(
    id                 varchar(50)                         not null
        primary key,
    create_time        timestamp default CURRENT_TIMESTAMP not null,
    update_time        timestamp default CURRENT_TIMESTAMP not null,
    row_version        int       default 0                 not null,
    delete_flag        int       default 0                 not null,
    emp_id             varchar(50)                         null comment '上传人的员工ID',
    photo_key          varchar(200)                        null comment '图片外链地址',
    temp_competitor_id varchar(50)                         null comment '临时竞品信息表ID'
)
    comment '竞品信息图片中间状态临时表';

-- auto-generated definition
create table sa_account_vcode_template
(
    id          varchar(50)                         not null
        primary key,
    create_time timestamp default CURRENT_TIMESTAMP not null,
    update_time timestamp default CURRENT_TIMESTAMP not null,
    row_version int       default 0                 not null,
    delete_flag int       default 0                 not null,
    v_code      varchar(255)                        null comment 'V码ID',
    account_id  varchar(50)                         null comment '门店ID'
)
    comment '门店与V码中间关系的临时存储表';

-- auto-generated definition
create table sa_visit_update_shop_temp
(
    id                 varchar(50)                         not null
        primary key,
    create_time        timestamp default CURRENT_TIMESTAMP not null,
    update_time        timestamp default CURRENT_TIMESTAMP not null,
    row_version        int       default 0                 not null,
    delete_flag        int       default 0                 not null,
    visit_id           varchar(50)                         not null comment '拜访ID',
    emp_id             varchar(50)                         not null comment '提交人员工ID',
    shop_booth         int                                 null comment '地堆数',
    shop_shelf         int                                 null comment '货架数',
    shop_bracket       int                                 not null comment '端架数',
    shop_booth_total   int                                 null comment '总地堆数',
    shop_shelf_total   int                                 null comment '总货架数',
    shop_bracket_total int                                 null comment '总端架数'
)
    comment '门店拜访更新门店数据中间表';

-- auto-generated definition
create table sa_visit_competitor_photo_connect
(
    id            varchar(50)                         not null
        primary key,
    create_time   timestamp default CURRENT_TIMESTAMP not null,
    update_time   timestamp default CURRENT_TIMESTAMP not null,
    row_version   int       default 0                 not null,
    delete_flag   int       default 0                 not null,
    visit_id      varchar(50)                         null comment '拜访ID',
    competitor_id varchar(50)                         null comment '竞品ID',
    photo_id      varchar(50)                         null comment '图片ID'
)
    comment '竞品图片与拜访关联表';



alter table gk_visit
    modify status varchar(30) not null;