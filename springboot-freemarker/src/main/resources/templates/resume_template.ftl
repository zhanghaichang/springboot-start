<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8"/>
    <style>
        * {
            outline: none;
        }

        body {
            margin: 0;
            padding: 0;
            font-size: 14px;
            font-family: "Hiragino Sans GB", "Microsoft Yahei", "SimSun", Arial, "Helvetica Neue", Helvetica;
            color: #333;
            word-wrap: break-word;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        article,
        aside,
        details,
        figcaption,
        figure,
        footer,
        header,
        hgroup,
        main,
        menu,
        nav,
        section,
        summary {
            display: block;
        }

        progress {
            display: inline-block;
            vertical-align: baseline;
        }

        a {
            color: #555;
            background-color: transparent;
            text-decoration: none;
        }

        a:hover {
            color: #00b38a;
        }

        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
            margin: 10px 0;
            font-weight: normal;
        }

        p {
            margin: 0;
        }

        p+p {
            margin-top: 10px;
        }

        img {
            border: 0;
            vertical-align: top;
            display: inline-block;
        }

        button,
        input,
        optgroup,
        select,
        textarea {
            margin: 0;
            padding: 0;
            border: 1px solid #ededed;
            border-radius: 0;
            font-family: "Hiragino Sans GB", "Microsoft Yahei", "SimSun", Arial, "Helvetica Neue", Helvetica;
        }

        textarea {
            overflow: auto;
        }

        label,
        select,
        button,
        input[type="button"],
        input[type="reset"],
        input[type="submit"],
        input[type="radio"],
        input[type="checkbox"],
        input[type="file"] {
            cursor: pointer;
        }

        input[type=text],
        input[type=password],
        textarea {
            font-family: "Microsoft Yahei", "SimSun", Arial, "Helvetica Neue", Helvetica;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }

        th,
        td {
            padding: 0;
        }

        strong {
            font-weight: normal;
        }

        em,
        i {
            font-style: normal;
        }

        dl,
        dt,
        dd {
            margin: 0;
        }

        ::selection {
            color: #fff;
            background-color: #00b38a;
        }

        ::-moz-selection {
            color: #fff;
            background-color: #00b38a;
        }

        ul,
        ol {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        #container {
            width: 720px;
            margin: 50px auto 54px auto;
            border: 1px solid #e3ebe9;
            border-radius: 5px;
        }

        .mr-template {
            padding: 50px 50px 10px;
        }

        .mr-template .mr-template_title {
            display: flex;
            justify-content: space-between;
            margin-bottom: 30px;
        }

        .mr-template .mr-template_title span {
            font-size: 18px;
            color: #333333;
            font-weight: 600;
            position: relative;
            margin-left: 10px;
        }

        .mr-template .mr-template_title span:before {
            content: '';
            display: block;
            border-left: 3px solid #00B38A;
            height: 16px;
            width: 3px;
            position: absolute;
            left: -7px;
            top: 50%;
            transform: translateY(-50%);
        }

        .mr-template .mr_md_del {
            display: none;
        }

        /***** 基本信息 ****/

        .basic {
            padding: 50px;
            background: #FAFAFA;
            border-radius: 5px 5px 0 0;
            border-bottom: 1px solid #E3EBE9;
            display: flex;
        }

        .basic .basic-photo {
            height: 88px;
            width: 88px;
            display: inline-block;
            position: relative;
        }

        .basic .basic-photo img {
            height: 88px;
            width: 88px;
            position: absolute;
            top: 0;
            border-radius: 50%;
        }

        .basic .basic-photo .basic-photo_shadow {
            display: none;
        }

        .basic .basic-photo:hover .basic-photo_shadow {
            display: block;
        }

        .basic .basic-info {
            position: relative;
            width: 100%;
            margin-left: 20px;
            flex: 1;
        }

        .basic .basic-info .edit-btn {
            position: absolute;
            right: 0;
            top: 4px;
        }

        .basic .basic-info .basic-name-area {
            overflow: hidden;
            height: 37px;
            margin-bottom: 11px;
        }

        .basic .basic-info .basic-name {
            float: left;
            max-width: 250px;
            font-size: 26px;
            color: #333333;
            font-weight: 600;
            line-height: 37px;
        }

        .basic .basic-info .icon-sex {
            width: 13px;
            height: 13px;
            float: left;
            margin-top: 16px;
            margin-left: 10px;
            background: none;
        }

        .basic .basic-info .icon-sex.icon-sex__male {
            background: url(https://www.lgstatic.com/common/image/pc/icon_man.png);
        }

        .basic .basic-info .icon-sex.icon-sex__female {
            background: url(https://www.lgstatic.com/common/image/pc/icon_woman.png);
        }

        .basic .basic-info .basic-self {
            margin-top: 20px;
        }

        .basic .basic-info p {
            font-size: 14px;
            color: #333333;
            line-height: 20px;
            margin-top: 6px;
        }

        .basic .basic-info i {
            display: inline-block;
            margin-right: 5px;
            vertical-align: middle;
        }

        .basic .basic-info .basic-tel_icon {
            width: 11px;
            height: 14px;
            margin-right: 2px;
            background: url(https://www.lgstatic.com/common/image/pc/icon_phone.png);
            display: inline-block;
            vertical-align: middle;
        }

        .basic .basic-info .basic-email_icon {
            width: 14px;
            height: 11px;
            margin-right: 2px;
            background: url(https://www.lgstatic.com/common/image/pc/icon_mail.png);
            display: inline-block;
            vertical-align: middle;
        }

        .basic .basic-info .basic-tel {
            margin-right: 20px;
        }

        .basic-tel span {
            vertical-align: middle;
        }

        .basic-email span {
            vertical-align: middle;
        }

        /***** 基本信息 end ****/

        /***** 自我描述 ****/

        .per .per-self_content {
            position: relative;
        }

        .per .per-self_content .per-self_des {
            margin-top: 13px;
            font-size: 14px;
            color: #666666;
            line-height: 30px;
        }

        .per .per-self_content .per-self_des p {
            margin-top: 0;
        }

        .per .per-self_content .per-self_des ol {
            list-style-type: decimal;
            -webkit-padding-start: 20px;
        }
        .per .per-self_content .per-self_des ul {
            list-style: disc;
            -webkit-padding-start: 20px;
        }

        /***** 自我描述 end ****/

        /***** 工作经历 ****/

        .work-exp .work-exp_list {
            position: relative;
            margin-bottom: 30px;
        }

        .work-exp .work-exp_list .exp-list_time {
            font-size: 14px;
            color: #666666;
            position: absolute;
            right: 0;
        }

        .work-exp .work-exp_list .exp-list_top {
            display: flex;
            align-items: center;
        }

        .work-exp .work-exp_list .exp-list_top img {
            width: 46px;
            height: 46px;
            margin-right: 10px;
        }

        .work-exp .work-exp_list .exp-list_top .exp-list_right {
            display: flex;
            flex-direction: column;
            flex: 1;
            height: 46px;
            justify-content: space-between;
        }

        .work-exp .work-exp_list .exp-list_top .exp-list_right p,
        .work-exp .work-exp_list .exp-list_top .exp-list_right strong {
            font-size: 14px;
            color: #333333;
        }

        .work-exp .work-exp_list .exp-list_top .exp-list_right strong {
            font-weight: 600;
        }

        .work-exp .work-exp_list .tagboard {
            margin: 20px 0 10px;
        }

        .work-exp .work-exp_list .exp-list_content {
            font-size: 14px;
            color: #666666;
            line-height: 30px;
        }

        .tagboard .tagboard-tag {
            font-size: 12px;
            color: #999;
            line-height: 18px;
            padding: 3px 10px;
            border: 1px solid #EAEDEC;
            border-radius: 100px;
            margin-right: 10px;
        }

        .work-exp .work-exp_list .exp-list_content ul, .work-exp .work-exp_list .exp-list_content_highlight ul {
            list-style: disc;
            -webkit-padding-start: 20px;
        }

        .work-exp .work-exp_list .exp-list_content ol, .work-exp .work-exp_list .exp-list_content_highlight ol {
            list-style-type: decimal;
            -webkit-padding-start: 20px;
        }
        /***** 工作经历 end ****/

        /***** 项目经历 ****/

        .pro-exp .pro-exp_list {
            position: relative;
            margin-bottom: 30px;
        }

        .pro-exp .pro-exp_list .exp-list_title {
            font-size: 14px;
            color: #333333;
            font-weight: 600;
            cursor: default;
            display: flex;
        }

        .pro-exp .pro-exp_list .exp-list_title .exp-project_name, .pro-exp .pro-exp_list .exp-list_title .exp-project_company span {
            max-width: 222px;
            display: block;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .pro-exp .pro-exp_list .exp-list_title .exp-project_company {
            display: flex;
        }

        .pro-exp .pro-exp_list .exp-list_time {
            font-size: 14px;
            color: #666666;
            position: absolute;
            right: 0;
            top: 0;
        }

        .pro-exp .pro-exp_list .exp-list_content,
        .pro-exp .pro-exp_list .exp-project_des {
            font-size: 14px;
            color: #666666;
            line-height: 30px;
            margin-top: 10px;
        }

        .pro-exp .exp-list_title.active:hover {
            color: #00B38A;
            cursor: pointer;
        }
        .pro-exp .exp-project_url {
            font-size: 14px;
            color: #666;
            line-height: 30px;
        }
        .pro-exp .pro-exp_list .exp-list_content ul, .pro-exp .pro-exp_list .exp-project_des ul {
            list-style: disc;
            -webkit-padding-start: 20px;
        }

        .pro-exp .pro-exp_list .exp-list_content ol,.pro-exp .pro-exp_list .exp-project_des ol{
            list-style-type:decimal;-webkit-padding-start:20px
        }

        /***** 项目经历 end ****/

        /***** 教育经历 ****/

        .edu-exp .edu-exp_list {
            position: relative;
            margin-bottom: 30px;
        }

        .edu-exp .edu-exp_list .exp-list_time {
            font-size: 14px;
            color: #666666;
            position: absolute;
            right: 0;
        }

        .edu-exp .edu-exp_list .exp-list_top {
            display: flex;
            align-items: center;
        }

        .edu-exp .edu-exp_list .exp-list_top img {
            width: 46px;
            height: 46px;
            margin-right: 10px;
        }

        .edu-exp .edu-exp_list .exp-list_top .exp-list_right {
            display: flex;
            flex-direction: column;
            flex: 1;
            height: 46px;
            justify-content: space-between;
        }

        .edu-exp .edu-exp_list .exp-list_top .exp-list_right p,
        .edu-exp .edu-exp_list .exp-list_top .exp-list_right strong {
            font-size: 14px;
            color: #333333;
        }

        .edu-exp .edu-exp_list .exp-list_top .exp-list_right strong {
            font-weight: 600;
        }


        /***** 教育经历 end ****/

        /***** 社交主页 ****/

        .social-page .social-page_list {
            margin-bottom: 30px;
            position: relative;
        }

        .social-page .social-page_list .social-page_content {
            display: flex;
        }

        .social-page .social-page_list .social-page_content img {
            width: 24px;
            height: 24px;
            margin-right: 10px;
            border-radius: 50%;
        }

        .social-page .social-page_list .social-page_content p {
            font-size: 14px;
            color: #666666;
        }


        /***** 社交主页 end ****/
                /***** 图片作品 ****/

        .portfolio-page .portfolio-list{
            margin-bottom: 30px;
            position: relative;
        }
        .portfolio-page .portfolio-list .portfolio-content{
            font-size: 14px;
            color: #666666;
        }


        /***** 图片作品 end ****/
    </style>
</head>

    <body>
        <div id="container">
            <!-- 基本信息 -->
            <div class="basic" id="baseInfo">
                <div class="basic-photo">
                    <img class="mr_headimg user-avatar" id="userpic" src="https://www.lgstatic.com/i/image2/M01/5F/7F/CgotOVs0lNGAIzkQAAA1029Ejh0993.png" width="120" height="120" alt="头像"
                    />
                </div>
                <div class="basic-info">
                    <div class="basic-name-area">
                        <p class="basic-name female">${name}</p>
                            <i class="icon-sex icon-sex__male"></i>
                    </div>
                                <p>
                                    <span class="basic-company">上海伊维金融信息股份有限公司</span>
                                    <span> / </span>
                                    <span class="basic-job">Java</span>
                                </p>
                            <p class="basic-self">
                                    <span class="basic-exp">5年工作经验 / </span>
                                    <span class="basic-edu">本科
                                         / 
                                    </span>
                                    <span class="basic-age">23岁</span>
                            </p>
                    <p>
                            <span class="basic-tel">
                                <i class="basic-tel_icon"></i>
                                <span>13585888888</span>
                            </span>
                            <span class="basic-email">
                                <i class="basic-email_icon"></i>
                                <span>zhanghaichang@163.com</span>
                            </span>
                    </p>
                </div>
            </div>
            <!-- /基本信息 -->

            <!-- 自我描述 -->
            <div class="mr-template per" id="perAbility">
                <div class="mr-template_title">
                    <span>自我描述</span>
                </div>
                <div class="tagboard integrative-tag">
                    <div class="integrative-tag_content">
                                <span class="tagboard-tag">责任心</span>
                    </div>
                </div>
                <div class="per-self_content">
                        <div class="per-self_des">
                            <p>相信我可以得。</p>
                        </div>
                </div>
            </div>
            <!-- /自我描述 -->


            <!-- 工作经历 -->
                <div class="mr-template work-exp" id="workExp">
                    <div class="mr-template_title">
                        <span>工作经历</span>
                    </div>
                    <ol class="work-exp_group">
                            <li class="work-exp_list">
                                <p class="exp-list_time">2011.10-至今</p>
                                <div class="exp-list_top">
                                    <img src="https://www.lgstatic.com/images/logo_default.png" alt="公司Logo"/>
                                    <div class="exp-list_right">
                                        <strong class="exp-list_title">
                                            <span>上海伊维金融信息股份有限公司</span>
                                             ／
                                                <span>IT</span>
                                        </strong>
                                        <p>Java</p>
                                    </div>
                                </div>
                                <div class="tagboard">
                                        <span class="tagboard-tag">后端</span>
                                        <span class="tagboard-tag">分布式</span>
                                        <span class="tagboard-tag">Java</span>
                                </div>
                                <div class="exp-list_content">
                                    <p>1、参与XX项目开发,该项目基于ExpressJS,Nunjucks作为服务端模版引擎,BaiduTemplate作为客户端模版引擎;</p>
<p>2、参与XX项目的开发,负责编写工具栏组件、弹窗组件和下拉菜单组件,基于SeaJS作为模版加载框架,Velocity作为服务端模版引擎,BaiduTemplate作为客户端模版引擎;</p>
<p>3、参与XX项目的开发,主要基于Echarts,使用ES6进行开发。</p>
<p></p>
                                </div>
                            </li>
                            <li class="work-exp_list">
                                <p class="exp-list_time">2019.01-2019.02</p>
                                <div class="exp-list_top">
                                    <img src="https://www.lgstatic.com/images/logo_default.png" alt="公司Logo"/>
                                    <div class="exp-list_right">
                                        <strong class="exp-list_title">
                                            <span>公司名称</span>
                                             ／
                                                <span>IT</span>
                                        </strong>
                                        <p>Java</p>
                                    </div>
                                </div>
                                <div class="tagboard">
                                        <span class="tagboard-tag">平台</span>
                                        <span class="tagboard-tag">Winform</span>
                                </div>
                                <div class="exp-list_content">
                                    <p>我任职XX公司XX部门，该部门后台系统主要由API模块，APP后台模块，算法模块以及数据模块组成。后台的所有任务通过分布式任务系统进行任务管理。</p>
<p>整套系统部署在公司的私有服务器上，主要基于公司的Mysql集群和Redis集群做数据存储，使用MQ集群做消息队列，基于ZK集群搭建高可用系统，前期我们服务之前的调用方式都是基于HTTP的方式，服务耦合性较高，后期我们基于Motan框架做了整个系统的微服务化。</p>
                                </div>
                            </li>
                    </ol>
                </div>
            <!-- /工作经历 -->

            <!-- 项目经历 -->
                <div class="mr-template pro-exp" id="proExp">
                    <div class="mr-template_title">
                        <span>项目经历</span>
                    </div>
                    <ol class="pro-exp_group">
                            <li class="pro-exp_list">
                                <p class="exp-list_title">
                                    <span class="exp-project_name">项目名称</span>
                                    <span class="exp-project_company">（<span>上海伊维金融信息股份有限公司</span>）</span>
                                </p>
                                <p class="exp-list_time">2019.01-2019.02</p>
                                <div class="exp-project_des"><p>1、参与XX项目开发,该项目基于ExpressJS,Nunjucks作为服务端模版引擎,BaiduTemplate作为客户端模版引擎;</p>
<p>2、参与XX项目的开发,负责编写工具栏组件、弹窗组件和下拉菜单组件,基于SeaJS作为模版加载框架,Velocity作为服务端模版引擎,BaiduTemplate作为客户端模版引擎;</p>
<p>3、参与XX项目的开发,主要基于Echarts,使用ES6进行开发。</p></div>
                                <div class="exp-list_content">
                                    <p>1、参与XX项目开发,该项目基于ExpressJS,Nunjucks作为服务端模版引擎,BaiduTemplate作为客户端模版引擎;</p>
<p>2、参与XX项目的开发,负责编写工具栏组件、弹窗组件和下拉菜单组件,基于SeaJS作为模版加载框架,Velocity作为服务端模版引擎,BaiduTemplate作为客户端模版引擎;</p>
<p>3、参与XX项目的开发,主要基于Echarts,使用ES6进行开发。</p>
                                </div>
                            </li>
                    </ol>
                </div>
            <!-- /项目经历 -->
                <!-- 教育经历 -->
                    <div class="mr-template edu-exp" id="eduExp">
                        <div class="mr-template_title">
                            <span>教育经历</span>
                        </div>
                        <ol class="edu-exp_group">
                                <li class="edu-exp_list">
                                    <p class="exp-list_time">2023-2023</p>
                                    <div class="exp-list_top">
                                        <img src="https://www.lgstatic.com/images/logo_default.png" alt="学校Logo"/>
                                        <div class="exp-list_right">
                                            <strong>学校名称</strong>
                                            <p>大专 / 专业名称</p>
                                        </div>
                                    </div>
                                </li>
                                <li class="edu-exp_list">
                                    <p class="exp-list_time">2011-2015</p>
                                    <div class="exp-list_top">
                                        <img src="https://www.lgstatic.com/i/image/M00/AD/4D/CgqKkVi1E2OAQTwUAAAOQDrOH7435.jpeg" alt="学校Logo"/>
                                        <div class="exp-list_right">
                                            <strong>北京航空航天大学</strong>
                                            <p>本科 / 经济学</p>
                                        </div>
                                    </div>
                                </li>
                        </ol>
                    </div>
                <!-- /教育经历 -->
        </div>
    </body>
</html>