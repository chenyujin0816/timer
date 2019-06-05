# 目录  
* [项目名称](#项目名称)
* [时间安排](#时间安排)
* [人员分工](#人员分工)
* [业务需求分析](#业务需求分析)
	1. [应用背景](#应用背景)
	2. [业务机遇](#业务机遇)
	3. [业务目标](#业务目标)
	4. [业务风险](#业务风险)
* [需求分析](#需求分析)
	1. [创建上下文图](#创建上下文图)
	2. [发现并建立DFD片段](#发现并建立DFD片段)
	3. [0层图](#0层图)
	4. [微规格说明](#微规格说明)
	5. [数据字典](#数据字典)
* [项目前景](#项目前景)
	1. [前景概述](#前景概述)
	2. [主要特性](#主要特性)
* [项目范围](#项目范围)
* [项目环境](#项目环境)
* [会议记录](#会议记录)
* [动态图](#动态图)
* [原型系统](#原型系统)

# 项目名称
时间管理助手 Timer
# 时间安排
### 第一周（5月5日~5月12日）
需求分析：  确认项目选题为“时间管理助手”，将项目命名为“Timer”，调研项目范围，与甲方进行第一次面谈，进行人员分工。  
项目代码：确定项目运行载体为Android平台的手机App，在github上创建技术博客。  
### 第二周（5月13日~5月19日）
需求分析：调研项目前景和范围，发放调查问卷收集信息并分析，与甲方进行第二次面谈，确认项目运行载体。  
项目代码：设计数据库，设计前端页面，编写前端页面，更新技术博客。  
### 第三周（5月20日~5月26日）
需求分析：将需求分析结构化，进行过程建模和数据建模（DFD图、ERD建模）  
项目代码：完成页面跳转和本地逻辑，完善前后端连接，合并两个程序员的代码并初步测试，更新技术博客。  
### 第四周（5月27日~6月2日）
需求分析：完成用例图和静态UML建模以及动态UML建模。  
项目代码：测试代码，修复尚未解决的bug。   
# 人员分工
石唯妍：管理任务分配，撰写项目文档，设计和监制项目。  
湛蓝蓝：撰写文档，设计软件，制作PPT。  
吴杰、宋溢：更新GitHub上的技术博客，编写UI、本地逻辑以及后端数据。  
赵池、万怡均：美工、设计UI，完成部分代码任务。  
# 业务需求
## 应用背景
  快节奏的生活带来机遇也带来压力，作为劳动主力的当代年轻人有大量事务堆积亟待完成。时间管理与任务安排成了抓住机遇的关键。在大学生中，据调查，他们给自己定的任务的完成度不到55%，未完成的有超过73.6%是半途而废，还有的是只有计划而没有实践。而在上班族中，85%的人没有具体的任务规划，只有根据工作任务定下的DDL。所以许多人由于自身的时间观念缺乏以及拖延症，上课上班迟到，作业与任务无法完成，规划的训练总是一推再推，在自我管理和提升方面的能力需要加强。大多数人在有管束有约束的情况下会获得事件的较好完成度，急需一个时间管理的APP。
## 业务机遇
如果我们开发的这款APP——TIMER能够让用户拥有清晰的任务时间线，根据实际需求规划未来一周或者一个月的安排，并在合适的时间给予提示、管理建议与奖励，满足用户关于时间规划的基本需求，那么用户任务完成度将会逐步上升。这也是TIMER的价值所在。

## 业务目标
理想任务完成率：80%，理想任务满意率：90%
最低任务完成率：60%，最低任务满意率：65%
注：任务完成率是用户完成每日完成任务占总任务的比重，任务满意率是用户在自我评分部分给自己的评价

## 业务风险
应用市场中有关APP太多，用户各自的需求不同，需要满足的需求也不同，TIMER的设计不足以满足所有需求，导致使用本产品的用户过少。
可能性：0.6 影响：9


# 业务需求分析
### 创建上下文图
| 业务需求        | 实现业务需求需要的系统特性        | 局部解决方案的对外交互 |
| --------------- |---------------| ---------|
| BR-1:任务设定      |SF-1：日期总览 | 外部输入：当前日期时间及日历内部输出：被选定日期 |
|    | SF-2：保存任务     |  外部输入：用户的任务内容外部输出：本地数据库的任务数据 |
|    | SF-3：修改任务     |  外部输入：用户的任务内容外部输出：本地数据库的任务数据 |
|  BR-2:任务管理建议   | SF-4：在截止日期之前额能够在通知栏提醒    |   内部输入：截止时间倒计时外部输出：操作系统广播通知 |
|  BR-3:分享   | SF-5：当前页面截图    |   外部输入：用户的分享指令外部输出：给操作系统的截图信号 |
|     | SF-6：截图保存    |   外部输入：指令完成提示内部输出：分享操作步骤提示 |
|  BR-4:当日评价   | SF-7：点亮任务评价星级    |   外部输入：用户当天评价外部输出：本地数据库的任务数据 |  

#### 上下文图  
![上下文图](https://github.com/jiecaojun/timer/blob/master/%E4%B8%8A%E4%B8%8B%E6%96%87%E5%85%B3%E7%B3%BB.png)
### 发现并建立DFD片段
需求与需要响应时间分析   

| &ensp;&ensp;事件&ensp;&ensp;   |   系统的响应      |
| ---- | ---- |
| 用户选择日期进行设定 | &ensp;&ensp;系统首先要显示日历界面，在用户选定之后进入指定日期界面，同时查看选定日期是否已经有任务，若有，则需从数据库中读出这个选定日期的任务数据并显示在日期的时间线上。 |
| 用户添加/修改任务 | 系统根据任务状态是否为空白，若非空白则将任务数据显示成任务信息，并接受用户的任务信息输入，保存成任务数据至数据库，显示在时间线上。 |
| 用户对当天任务进行评价 | 系统保存信息到数据库，并显示相应的星星。 |
| 用户通过分享按钮发出请求 | 系统向操作系统发出截图请求，接收到截图成功的提示后，显示分享的操作步骤。 |
| 任务快要到期 | 系统根据任务截止状态倒计时向系统发送广播，请求将即将截止的任务显示在通知栏。 |

DFD片段图  
a)  
![A](https://github.com/jiecaojun/timer/blob/master/DFD(a).png)  
b)  
![B](https://github.com/jiecaojun/timer/blob/master/DFD(b).png)  
c)  
![C](https://github.com/jiecaojun/timer/blob/master/DFD(c).png)  
d)  
![D](https://github.com/jiecaojun/timer/blob/master/DFD(d).png)  
e)  
![F](https://github.com/jiecaojun/timer/blob/master/DFD(F).png)  
### 0层图  
![0层图](https://github.com/jiecaojun/timer/blob/master/_0层图.png)
### 微规格说明
过程1. 接受用户任务信息输入：  
&ensp;&ensp;IF 任务状态为已有状态，说明是修改任务  
&ensp;&ensp;&ensp;&ensp;跳转到详情页面，并将原有任务内容显示  
&ensp;&ensp;ELSE  
&ensp;&ensp;&ensp;&ensp;直接跳转到详情界面  
&ensp;&ensp;ENDIF  
&ensp;&ensp;DO WHILE 用户还在输入  
&ensp;&ensp;&ensp;&ensp;保持输入状态  
&ensp;&ensp;END DO  
&ensp;&ensp;将任务信息保存下来，并跳转到时间线界面，显示当日所有已经添加任务  

过程2. 接受用户日期选择信息：  
&ensp;&ensp;IF 用户点击日期  
&ensp;&ensp;&ensp;&ensp;跳转到相应选择日期并输出时间线以及已有任务  
&ensp;&ensp;ELSE  
&ensp;&ensp;&ensp;&ensp;回到上一界面  
&ensp;&ensp;END IF  

过程3. 任务广播到通知栏  
&ensp;&ensp;IF 任务在指定完成时间前一段设定好的时间还没有完成：  
&ensp;&ensp;&ensp;&ensp;系统向操作系统发送广播，请求将任务显示在通知栏中  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;IF 操作系统同意请求  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;显示  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ELSE  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;在下次打开系统时请求权限  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;END IF  
&ensp;&ensp;END IF  

过程4. 对当日进行评价  
&ensp;&ensp;IF 接收到了用户对当日的评价信息  
&ensp;&ensp;&ensp;&ensp;生成评价数据存入数据库  
&ensp;&ensp;&ensp;&ensp;将评价信息用星星的方式显示出来  
&ensp;&ensp;END IF  

过程5. 分享  
&ensp;&ensp;IF 用户选择分享今日任务及自己的评价  
&ensp;&ensp;&ensp;&ensp;WHILE 系统向操作系统发送截屏请求  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;IF 操作系统截屏成功  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;系统用TOAST方式告诉用户将截图通过社交方式发送即可分享  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;BREAK  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ELSE  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;系统显示截图失败，并请求开启权限  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;IF 用户拒绝开启  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;BREAK  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;END IF  
&ensp;&ensp;END IF  
### 数据字典
任务表单数据=任务创建日期+任务结束日期+任务名称+任务详情+任务ID  
&ensp;&ensp;&ensp;&ensp;任务创建日期=年份+日期+时间  
&ensp;&ensp;&ensp;&ensp;任务结束日期=年份+日期+时间  
&ensp;&ensp;&ensp;&ensp;任务名称=0{字符}100  
&ensp;&ensp;&ensp;&ensp;任务详情=0{字符}500  
&ensp;&ensp;&ensp;&ensp;任务ID=0{NUMBER}10000  
&ensp;&ensp;&ensp;&ensp;NUMBER=[0|1|2|3|4|5|6|7|8|9]  
&ensp;&ensp;评价信息数据=评价星级  
&ensp;&ensp;&ensp;&ensp;评价星级=[0|1|2|3|4|5]  
&ensp;&ensp;任务状态=任务ID  

# 项目前景
## 前景概述
对于需要时间管理的用户来说，TIMER是一个能够计划他们的任务与目标或者说规划他们未来生活的应用。当TODO列表被确定之后，TIMER会进行倒计时来增加用户的紧迫感。这种紧迫感恰恰是用户需要的，来给他们压力完成任务的关键性存在。在完成任务后，用户可以获得成就感与幸福感。

## 主要特性

| 编号 | 特性 |
| - | - |
FE-1 | 用户可以自定义任务内容、起始与结束时间
FE-2 | 会对任务进行完成倒计时
FE-3 | 会对每天的自我满意度进行评价
FE-4 | 可以对未来任何时间进行规划
FE-5 | 有一条完整的时间规划线
FE-6 | 轻量级应用
FE-7 | 对单个任务重复编辑
FE-8 | 与他人互动，互相鼓励与评价
FE-9 | 可以自动截图
FE-10 | 可以连接与社交平台的接口进行分享

# 项目范围

| 特性 | 版本1 | 版本2 |  
| - | - | -  |
| FE-1 | 创建本地数据库|  	
FE-2 | Y	
FE-3 | Y	
FE-4 | 导入日历	
FE-5 | UI设计需要清晰明了	
FE-6 | 使用最少的页面完成最多的任务	
FE-7 | Y	
FE-8 | N | Y
FE-9 | Y	
FE-10 | N | Y

# 项目环境
## 操作环境
	Android 4.0及以上
## 涉众及项目属性

### 涉众概要
#### 业务目标 
业务目标是开发一款适合辅助同学们合理安排时间的时间管理助手系统，使用户更好的规划时间，合理平衡学习工作生活娱乐三者之间的关系。 
#### 发现和定义涉众
 |信息来源 | 通过与客户访谈获取 |
| - | - |
|注意 |	准确描述涉众情况和对系统建设的期望而非细节|
|用途 |	涉众说明是需求收集的方向，涉众期望则是检验系统成功的标准|
|涉众 |	与要建设的业务系统有关的一切人和事|
|业主 |	系统的出资方|
|业务提出者 | 业务规则、业务模式提出方|
|业务管理者 | 实际管理人员和监督人员|
|业务执行者 | 业务操作人员|
|第三方 | 第三方不需要细化，仅仅作为一个整体来纳入涉众|
|承建方 | 我方|
|用户 | 预期的系统使用者|
|相关法律法规 | 一些建设标准|
|文档维护 | 自项目开始一直持续到项目结束|
#### 时间管理助手系统涉众概要
|编号| 涉众名称 |涉众说明 | 期望|
|-|-|-|-|
|SH001|	普通用户|	普通用户是指使用该助手安排时间，并且对自己当天完成情况进行评价的用户，通常为学生。|	1.登录该系统2.通过系统安排自己的时间并分配任务。3.通过系统对自己每天的任务完成情况进行打分4.通过系统对自己当天的任务完成情况进行分享至社交网络|
|SH002 | 管理员 | 	管理员是指维护系统正常运行并且管理用户信息的管理人员。|1.维护系统基本功能的运行 |

### 涉众简档
#### 涉众简档说明文档
| 信息来源 | 客户访谈 |
| - | - |
|什么是涉众简档|	描述涉众在系统中承担的职责，以及涉众在系统中的成功标准|
|作用|	提示下一步需求分析应当找谁、从什么方面、了解哪些业务|
|注意|	着重描述涉众在其业务岗位上的职责以及完成职责的标准|
|维护|	最重要是维护涉众的岗位职责和在系统过程中的参与内容和可交付工件的清单|

#### 时间管理助手系统涉众简档
|涉众|	SH001 普通用户|
| - | - |
|涉众代表|	张雅彬|
|特点	|系统的预期使用者，会使用手机。|
|职责	|1.登录该系统                                         2.通过系统安排自己的时间并分配任务。  3.通过系统对自己每天的任务完成情况进行打分    4.通过系统对自己当天的任务完成情况进行分享至社交网络|
|成功标准	|1.登录该系统|
|参与|	界面设计的需求及建议|
|可交付工作|	无|
| 意见/问题	|希望系统的界面以简约风格为主|

|涉众|SH002管理员|
| - | - |
|涉众代表|	石唯妍|
|特点|	系统的主要使用者之一，应具备相应的计算机操作水平，可培训|
|职责|	1.维护系统的基本功能运行|
|成功标准|	1.系统正常运行|
|参与|	参与维护系统的正常运行|
|可交付工件	|维护网站基本功能的运行|
|意见/问题|	无|

### 用户概要
#### 用户概要说明文档
| - |   |
| - | - |
|用途|	是系统中建立业务角色的重要来源，对权限管理有很重要的指导意义，一般情况下，业务需求来自涉众，界面需求很可能来自预期的系统用户|
|什么是用户概要 | 一般包括用户概况、特点和用户使用系统的方式|

#### 时间管理助手用户概要
|编号|	用户名称 |	用户概况和特点	|使用系统方式	|代表涉众|
| - | - | - | - | - |
|US001|	普通用户|	普通用户通过该系统安排自己的时间，进行评分及分享打卡。用户主要是北京理工大学的学生，会使用手机。|	1.通过手机APP使用该系统 |SH001|
|US002	|管理员	|管理员主要负责维护系统的正常运行，是系统的主要使用者之一，应具备相应计算机操作水平，可培训	|1.通过系统后台电脑网页使用该系统 |	SH002|
### 用户简档
#### 用户简档说明文档
|-| |
|-|-|
|用户简档用途|	将一些典型的用户代表的一些信息描述出来，在进行分析设计时，可以根据用户代表的使用习惯、特点等来进行有针对性的设计|

#### 时间管理助手用户简档
|用户	|US001 普通用户|
|-|-|
|用户代表|	张雅彬|
|说明|	系统的的主要使用者，手机的使用者|
|特点|	计算机系统的预期使用者，无法衡量计算机使用水平，也无法培训|
|职责|	1.登录该系统2.通过系统安排自己的时间并分配任务。3.通过系统对自己每天的任务完成情况进行打分4.通过系统对自己当天的任务完成情况进行分享至社交网络|
|成功标准|	登录该系统|
|参与|	界面设计|
|可交付工件|	《界面设计》|
|意见/问题|	无|
|用户|	US002 管理员|
|用户代表	|石唯妍|
|说明	|系统的主要使用者之一，应具备相应的计算机操作水平，可培训|
|特点	|无|
|职责|	维护网站基本功能的运行。|
|成功标准|	网站正常运行|
|参与|	参与网站基本功能的运行。|
|可交付工件|	维护网站基本功能的运行。|
|意见/问题|	无|
### 消费者统计
#### 消费者统计说明文档
|用途|	这些统计数据有些会对系统建设起到限制|
|-|-|
|文档内容|	说明系统的预期使用人群、特点，使用系统的频率和方式，对系统的普遍期望|

#### 时间管理助手消费者统计
|消费者名称|消费者概况和特点|应用环境|使用频率|特殊要求|  
| - | - | - | - |  -|
|直接操作用户|注册用户直接使用该软件，群体数量较大，使用频率频繁|手机移动端（Android）|每天|对系统的易用性有较高的要求|  
|间接用户|第三方平台商家通过广告投放获取商机|手机移动端（Android）|每天|需要广大的受众群体|

### 涉众期望的优先级
#### 涉众优先级
（1）最高优先级（数值2）：普通用户(S1:SH001)  
（2）普通优先级（数值1）：系统管理员(S2:SH002)  
#### 期望优先级
（1）最高优先级（数值2）：  
1.维护系统基本功能的运行(F1)  
（2）普通优先级（数值1）：  
1.登录该系统(F2)    
2.通过系统安排自己的时间并分配任务。(F3)  
3.通过系统对自己每天的任务完成情况进行打分(F4)  
4.通过系统对自己当天的任务完成情况进行分享至社交网络(F5)  
下图中9代表第一优先级，6代表第二优先级。  

| 涉众期望 | S1(3) | S2(3) |  
| - | - | - |  
| F1(3) | 9 | 0 |  
| F2(3) | 0 | 9 |  
| F3(3) | 0 | 9 |  
| F4(2) | 6 | 0 |  
| F5(2) | 6 | 0 |  

# 人员分工

# 会议记录
## 甲乙双方第一次见面会议记录
会议时间：2019/5/8 18:00  
会议地点：徐特立图书馆一层  
参会人员：    
	甲方：张雅彬、张慧昕  
	乙方：赵池、万怡均  
会议目的：
1. 熟悉双方人员，确立合作关系。
2. 详细介绍项目，双方交换意见。
3. 拟定项目计划，安排二次会议。
会议内容：
1. 双方自我介绍，互相取得联系方式，并建立QQ联系群。
2. 甲方提出：  
	a)项目名称：时间管理助手  
	b)项目背景：当代普通大学生普遍缺乏时间观念，不能有效利用时间，因此需要一个“助手”帮助他们进行时间管理。  
	c)针对人群：做事没有计划，缺乏时间观念的大学生。  
	d)用户需求:   
		1）用户可以提前规划后一天要做的事情。  
		2）用户可以回顾之前一段时间做了哪些事情，又有哪些事情要做却没做。  
		3）用户可以对每天的任务完成情况进行打分反馈。  
		4）用户可以记录当时的心情与想法。  
		5）用户可以与他人相互交流监督分享心得。  
   乙方提出：  
	e)针对产品使用人群对“时间管理”的行为特征进行调研，完善功能需求。  
	f)针对产品使用人群对手机app的使用偏好进行调研，帮助完成产品设计。  
	g)针对市场上已存在的“备忘录”等相似产品进行调研，结合用户需求对已存在产品出现的问题提出改进，确保新产品的完备性和合理性。  
3. 制定项目未来几周计划：
4. 预计下周安排第二次会议，具体时间地点QQ群内另行安排。
## 第一次内部会议  
会议时间：2019/5/13 14:30  
会议地点：理教二楼小沙发  
会议记录人：湛蓝蓝  
参会人员：石唯妍，湛蓝蓝，吴杰，宋溢，赵池，万怡均  
主要议题：确定本周具体目标  
会议结果：  
	a)  UI——赵池，万怡均  
	b)  和甲方沟通更改分享功能，和朋友分享改成分享到朋友圈，qq空间，微博！！！细化需求！！——吴杰，宋溢  
	c） 明确具体的功能要点和跳转逻辑，流程图  
	d） 安卓框架实现——宋溢  
	e）项目前景文档——湛蓝蓝，涉众分析和硬数据采集——石唯妍  
## 甲乙双方第二次见面会议记录  
会议时间：2019/5/15 13:30  
会议地点：徐特立图书馆一层  
会议记录人：吴杰  
参会人员：    
&ensp;&ensp;&ensp;&ensp;&ensp;甲方：主文浩    
&ensp;&ensp;&ensp;&ensp;&ensp;乙方：吴杰，宋溢  
缺席人员： 无  
#### 主要议题：  
1. 甲乙双方对会议上一次遗留的、未讨论的问题进行讨论。
2. 乙方反馈需求，对项目进行进一步调整。  
3. 甲方细化需求，完善对项目的需求细则。  
  
#### 与会者发言：  
  
吴&ensp;&ensp;杰（乙方）：在上一次会议中，甲乙双方未讨论关于项目实现的载体，只讨论了产品的方向与需求框架，我想询问甲方希望使用什么作为项目的载体？  
主文浩（甲方）：我们希望使用手机APP作为项目载体。  
吴&ensp;&ensp;杰（乙方）：作为移动端，按照时间周期以及开发成员的开发能力来看，我们只能保证Android端的功能实现，IOS端实现需要后续的拓展。  
主文浩（甲方）：可以先完成Android端的APP，IOS端不着急。  
  
主文浩（甲方）：我们想要增加一个上次没有提到的功能，我们想要在时间助手中加入到时提醒的功能。
宋&ensp;&ensp;溢（乙方）：具体呢，怎么提醒？在时间戳到达之后在状态栏发出通知可以吗？  
主文浩（甲方）：这样很好，但是要加上铃声提醒，就像闹钟一样，只有通知栏不足以引起用户的注意。  
宋&ensp;&ensp;溢（乙方）：可以，我们可以实现。  
  
吴&ensp;&ensp;杰（乙方）：关于产品界面，你们有更细致的要求吗？比如说风格，比如说配色，或者布局样式？  
主文浩（甲方）：对于风格，我们想要一种简约风格的产品，不要太花俏太华丽。对于配色你们可以自由发挥，注意风格就可以。  
主文浩（甲方）：你们现在有关于界面的设计了吗？我能不能看一看？  
吴&ensp;&ensp;杰（乙方）：我们的界面正在设计之中，我相信周五你们一定可以看到我们的项目原型。I promiss。  
主文浩（甲方）：我们希望界面能美观大方，给用户的第一印象很重要。  
  
宋&ensp;&ensp;溢（乙方）：我们有一个关于需求方面的问题需要作出改动。  
主文浩（甲方）：请讲。  
宋&ensp;&ensp;溢（乙方）：关于将日程与好友分享的功能，我们有意改为将日程分享给QQ好友、微信好友、QQ空间、微信朋友圈或者微博这些平台。
主文浩（甲方）：为什么不直接分享给APP平台内的好友呢？我们觉得APP内的分享就很不错啊。  
宋&ensp;&ensp;溢（乙方）：对于用户来说，如果想要达到多人监督的效果完全可以使用QQ空间以及微信朋友圈的分享来达到，单对单的分享也很实用。
而且一旦我们采用了这种方式，在实现需求的同时，我们能够大大降低项目的代码难度，我们将有更充足的时间对APP进行精雕细琢。  
主文浩（甲方）：好吧，可以换一种方式，但是不可以影响我们需求的实现。  
  
#### 会议结果：
1.甲方提出：  
&ensp;&ensp;&ensp;&ensp;&ensp;a) 增加通知提醒的需求：用户会在任务当天收到通知栏提醒。   
&ensp;&ensp;&ensp;&ensp;&ensp;b) 希望能对界面进行美化， 期望简约的风格。  
2.乙方提出：  
&ensp;&ensp;&ensp;&ensp;&ensp;取消app内朋友分享及评论功能，改为第三方平台分享。（甲方表示同意）  
3.保持观点：  
&ensp;&ensp;&ensp;&ensp;&ensp;甲方想要只管看到产品界面，乙方暂时无法提供，需要在下一次会议展示。  

# 动态图
## 系统顺序图
1、上下文环境    
&ensp;&ensp;a)用户打开TIMER进行操作。  
2、交互对象  
&ensp;&ensp;a)TIMER  
&ensp;&ensp;b)用户
3、用例描述  
&ensp;&ensp;a)用户打开TIMER进行操作。  
&ensp;&ensp;b)新建任务，输入任务详情，设定时间。  
&ensp;&ensp;c)查看任务。  
&ensp;&ensp;d)修改或删除任务。  
&ensp;&ensp;e)对当日任务完成度进行评价。  
&ensp;&ensp;f)分享。  
&ensp;&ensp;b)c)d)应该是循环并行消息。  
4、消息  

| 消息 | 用户行为 | 系统返回 |
|  -   |   -     |    -    |
|  设定任务 | 新建任务，填入具体内容设定并点击保存 |显示任务标题，倒计时|
|查看任务|点击已有任务|显示任务详情。|
|修改任务|点击已有任务，删除或修改任务详情|显示任务标题，倒计时|
|当日评价|点击评价栏并设定星级显示星级|显示星级|
|分享|点击分享|分享成功与否|

5、具体UML图
![UML图]()
## 状态图
1、可能存在的稳定状态  
&ensp;&ensp;主界面状态：  
&ensp;&ensp;&ensp;&ensp;进入系统时的第一状态  
&ensp;&ensp;&ensp;&ensp;用户除了滑动时间线之外没有进行其他的操作时。  
&ensp;&ensp;&ensp;&ensp;评价/分享时的底层状态。  
&ensp;&ensp;&ensp;&ensp;浏览/创建/修改/删除任务完成后返回到这个状态。  
&ensp;&ensp;&ensp;&ensp;选择日期后进入当选择时间的主界面状态。  
&ensp;&ensp;日历状态    
&ensp;&ensp;&ensp;&ensp;用户选择时期时出现的状态  
&ensp;&ensp;浏览状态  
&ensp;&ensp;编辑状态   
&ensp;&ensp;&ensp;&ensp;用户创建新的任务。  
&ensp;&ensp;&ensp;&ensp;用户编辑任务，此时应有任务详情显示  
&ensp;&ensp;分享状态  
&ensp;&ensp;评价状态  
2、建立状态转换  
|  | 主界面 | 浏览 | 编辑 | 日历 | 分享 | 评价 |
|-|-|-|-|-|-|-|
|主界面|Y|Y|Y|Y|Y|Y|
|浏览|Y||||||
|编辑|Y|Y|||||
|日历|Y||||||
|分享|Y||||||
|评价|Y||||||

3、状态抓换图  
![状态转换图]()  
三、活动图  
![活动图]()  
四、Timer类图  
![类图]()  
五、用例图  
![用例图]()  

# 原型系统
主界面，可以查看当天日程  
![main](https://github.com/everks/timer/blob/dev/app/Main.jpg)  
日历界面，进入后可选日期  
![calenser](https://github.com/everks/timer/blob/dev/app/calender.jpg)  
编辑界面，编辑某个活动的信息  
![add](https://github.com/everks/timer/blob/dev/app/add.jpg)  
浏览界面，浏览某个活动的信息  
![scan](https://github.com/everks/timer/blob/dev/app/scan.jpg) 
分享至朋友圈  
![share](https://github.com/everks/timer/blob/dev/app/share.jpg)  
编辑信息有限制  
![failure](https://github.com/everks/timer/blob/dev/app/failure.jpg)  
