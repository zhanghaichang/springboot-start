# QUARTZ
> Quartz是OpenSymphony开源组织在Job scheduling领域又一个开源项目，完全由Java开发，可以用来执行定时任务，如：定时发送信息、定时生成报表等等。类似于java.util.Timer。但是相较于Timer， Quartz增加了很多功能：

* 持久性作业 - 就是保持调度定时的状态;
* 作业管理 - 对调度作业进行有效的管理;


## QUARTZ的体系结构简介：

> Quartz框架主要核心组件包括调度器、触发器、作业。调度器作为作业的总指挥，触发器作为作业的操作者，作业为应用的功能模块。

* Scheduler：调度任务的主要API
* ScheduleBuilder：用于构建Scheduler，例如其简单实现类SimpleScheduleBuilder
* Job：调度任务执行的接口，也即定时任务执行的方法
* JobDetail：定时任务作业的实例
* JobBuilder：关联具体的Job，用于构建JobDetail
* Trigger：定义调度执行计划的组件，即定时执行
* TriggerBuilder：构建Trigger     


Job为作业的接口，为任务调度的对象；JobDetail用来描述Job的实现类及其它相关的静态信息；Trigger做为作业的定时管理工具，一个Trigger只能对应一个作业实例，而一个作业实例可对应多个触发器；Scheduler做为定时任务容器，是quartz最上层的东西，它提携了所有触发器和作业，使它们协调工作，每个Scheduler都存有JobDetail和Trigger的注册，一个Scheduler中可以注册多个JobDetail和多个Trigger。


#### JOB

Job是一个接口，只有一个方法void execute(JobExecutionContext context)，被调度的作业（类）需实现该接口中execute()方法，JobExecutionContext类提供了调度上下文的各种信息。每次执行该Job均重新创建一个Job实例;


#### JOBDETAIL

Quartz在每次执行Job时，都重新创建一个Job实例，所以它不直接接受一个Job的实例，相反它接收一个Job实现类，以便运行时通过newInstance()的反射机制实例化Job。因此需要通过一个类来描述Job的实现类及其它相关的静态信息，如Job名字、描述、关联监听器等信息，JobDetail承担了这一角色。JobDetail 用来保存我们作业的详细信息。一个JobDetail可以有多个Trigger，但是一个Trigger只能对应一个JobDetail。


#### TRIGGER

Trigger是一个类，描述触发Job执行的时间触发规则。主要有SimpleTrigger和 CronTrigger这两个子类。当仅需触发一次或者以固定时间间隔周期执行，SimpleTrigger是最适合的选择；而CronTrigger则 可以通过Cron表达式定义出各种复杂时间规则的调度方案：如每早晨9:00执行，周一、周三、周五下午5:00执行等；Springboot中applicationContext.xml配置


#### SCHEDULER

Scheduler负责管理Quartz的运行环境，Quartz它是基于多线程架构的，它启动的时候会初始化一套线程，这套线程会用来执行一些预置的作业。Trigger和JobDetail可以注册到Scheduler中；Scheduler可以将Trigger绑定到某一JobDetail中，这样当Trigger触发时，对应的Job就被执行。Scheduler拥有一个SchedulerContext，它类似于ServletContext，保存着Scheduler上下文信息，Job和Trigger都可以访问SchedulerContext内的信息。Scheduler使用一个线程池作为任务运行的基础设施，任务通过共享线程池中的线程提高运行效率。 
