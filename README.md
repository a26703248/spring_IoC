# spring_IoC
### Spring Bean refresh 流程

![Alt refresh](.\src\main\resources\images\Image 1.png)
1. prepareRefresh();刷新前的預先處理
   ![Alt prepareRefresh](.\src\main\resources\images\Image 2.png)
   * getEnvironment初始化屬性設定;子類自訂義個人屬性設置方法
   * earlyApplicationEvent().validateRequiredProperties();驗證屬性合法性
   * earlyApplicationEvents = new LinkHashSet&lt;ApplicationEvent&gt;();保存初期事件
2. obtainFreshBeanFactory();獲得BeanFactory
   ![Alt obtainFreshBeanFactory](.\src\main\resources\images\Image 4.png)
   * refreshBeanFactory();刷新建立BeanFactory
   * 建立 this.beanFactory = new DefaultListableBeanFactory() ;設定Id
   * getBeanFactory();返回 GenericApplicationContext 建立的 BeanFactory 物件
   * 將建立的 BeanFactory(DefaultListableBeanFactory) 返回,
3. prepareBeanFactory(beanFactory);BeanFactory 的準備工作(BeanFactory 準備工作)
   ![Alt prepareBeanFactory](.\src\main\resources\images\Image 5.png)
   * 設定 BeanFactory 的 ClassLoader、表達式解析器
   * 添加部分 BeanPostProcessor(ApplicationContextAwareProcessor)
   * 設定忽略自動裝配的介面 EnvironmentAware、EmbeddedValueResolverAware、...等
   * 註冊可解析的自動裝配，讓物件能夠自動注入，BeanFactory、ResourceLoader
     、ApplicationEventPublisherAware、MessageSourceAware、ApplicationContextAware
     、ApplicationStartupAware
   * 添加 BeanPostProcessor(ApplicationListenerDetector) ... 等
   * 添加編譯時的 AspectJ
   * BeanFactory 註冊一些環境物件environment、systemProperties、systemEnvironment、applicationStartup
4. postProcessBeanFactory(beanFactory);BeanFactory 準備工作完成的後置器處理
   ![Alt postProcessBeanFactory](.\src\main\resources\images\Image 6.png)
   * 子類可同過複寫 postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) 方法增加一些準備工作
5. invokeBeanFactoryPostProcessors(beanFactory);執行所有繼承 BeanPostProcessor 子類
   ![Alt invokeBeanFactoryPostProcessors](.\src\main\resources\images\Image 7.png)
   * BeanPostProcessor: BeanFactory 後置處理器，在 BeanFactory 準備工作完成後執行兩個介面
     BeanDefinitionRegistryPostProcessor、BeanPostProcessor
   ![Alt invokeBeanFactoryPostProcessors](.\src\main\resources\images\Image 9.png)
   * 執行 BeanPostProcessor 的方法
     * 獲得所有 BeanDefinitionRegistryPostProcessor
     * 優先執行時做 PriorityOrdered 介面的 BeanDefinitionRegistryPostProcessor、
       postProcessor、postProcessBeanDefinitionRegistry(registry)
     * 在執行時做 Ordered 順序的介面 BeanDefinitionRegistryPostProcessor、
       postProcessor、postProcessBeanDefinitionRegistry(registry)
     * 最後執行未實做任何順序的 BeanDefinitionRegistryPostProcessor、
       postProcessor、postProcessBeanDefinitionRegistry(registry)
   * 在執行 BeanFactoryPostProcessor 的方法
      * 獲得所有 BeanFactoryPostProcessor
      * 優先執行時做 PriorityOrdered 介面的 BeanDefinitionRegistryPostProcessor、
        postProcessor、postProcessBeanDefinitionRegistry(registry)
      * 在執行時做 Ordered 順序的介面 BeanDefinitionRegistryPostProcessor、
        postProcessor、postProcessBeanDefinitionRegistry(registry)
      * 最後執行未實做任何順序的 BeanDefinitionRegistryPostProcessor、
        postProcessor、postProcessBeanDefinitionRegistry(registry)
6. registerBeanPostProcessors(beanFactory);註冊 BeanPostProcessor(Bean的後置處理器)，攔截 Bean 建立的過程
    ![alt registerBeanPostProcessors](.\src\main\resources\images\image 10.png)
   * 不同類型的 BeanPostProcessor，在 Bean 建立前後的執行時間也不同  
     BeanPostProcessor  
     InstantiationAwareBeanPostProcessor  
     DestructionAwareBeanPostProcessor  
     SmartInstantiationAwareBeanPostProcessor  
     MergedBeanDefinitionPostProcessor
     * 獲得所有的 BeanPostProcessor;BeanPostProcessor 也支持 Ordered、PriorityOrdered 排序
     * 先註冊 PriorityOrdered 的 BeanPostProcessor，把每個 BeanPostProcessor，添加到 
       BeanFactory 中
     * 在註冊 Ordered 介面 BeanPostProcessor
     * 在註冊執行未實做任何順序的 BeanPostProcessor
     * 在註冊 MergedBeanDefinitionPostProcessor
     * 最終註冊 ApplicationListenerDetector，來在 Bean 檢查是否為 ApplicationListenerDetector
       * 若為是則儲存置 ApplicationListenerList 中
7. initMessageSource();初始化MessageSource物件(國際化、消息綁定、消息解析)
   ![alt registerBeanPostProcessors](.\src\main\resources\images\image 11.png)
   * 獲得 BeanFactory
   * 尋找容器中是否有 MessageSource 覆值給 messageSource，沒有則會建立 DelegatingMessageSource
     取得國際化設定文件中的 key值，能按區域訊息取得
   * 把建立好的 MessageSource 註冊製容器中，後續獲得國際化設定檔的時候，可自動注入 MessageSource
8. initApplicationEventMulticaster();初始化事件發送器
   ![alt registerBeanPostProcessors](.\src\main\resources\images\image 12.png)
   * 獲得所有的 BeanPostProcessor
   * 從 BeanFactory 尋找 applicationEventMulticaster 的 ApplicationEventMulticaster
   * 沒有則建立 SimpleApplicationEventMulticaster
   * 將 applicationEventMulticaster 物件註冊至容器中
9. onRefresh();
   ![alt registerBeanPostProcessors](.\src\main\resources\images\image 13.png)
   * 保留給子類別複寫使用，在容器更新的時候自訂義邏輯
10. registerListeners(); 註冊所有監聽器
    ![alt registerBeanPostProcessors](.\src\main\resources\images\image 14.png)
    * 取得所有 ApplicationListener
    * 將每個監停器註冊到 ApplicationEventMulticaster 中
    * 發送之前發生的動作
11. finishBeanFactoryInitialization(beanFactory);初始化所有剩下的 Bean
    ![alt registerBeanPostProcessors](.\src\main\resources\images\image 15.png)
    * 獲得容器中的所有 Bean，依序進行初始化和建立物件
    * 獲得 Bean 的定義訊息，RootBeanDefinition
    * 判斷 Bean 是否為抽象、單例、懶加載
      * 判斷是否為 FactoryBean，是則使用工廠模式建立，不是則使用 getBean() 建立
      * 建立物件
        1. getBeam()
        2. doGetBean(name, null, null, false)
        3. 先尋找容器是否已被建立過
        4. 若搜尋不到則開始調用 FactoryBean 建立
        5. 標記當前 Bean 已被建立(方只多執行緒)
        6. 取得當前 Bean 所依賴的 Bean並將其建立
        7. 使用單例 FactoryBean 建立 Bean 物件
            * createBean(beanName, mdb, args)
            * Object bean = resolveBeforeInstantiation() 讓 BeanPostProcessor 攔截 Bean 建立流程  
              InstantiationAwareBeanPostProcessor 提前執行呼叫 postProcessBeforeInstantiation 方法  
              如果有返回值則呼叫 postProcessAfterInitialization() 方法
            * 如果前面的 InstantiationAwareBeanPostProcessor 沒返回代理物件則會 doCreateBean(beanName, mdb, args) 建立
              * doCreateBean 內部呼叫 getBeanInstance(beanName, mdb, args) 利用工廠模式建立 Bean
              * applyMergeBeanDefinitionPostProcessor(mbd, beanType, beanName)，會呼叫 MergedBeanDefinitionPostProcessors 中
                postProcessMergedBeanDefinition 方法
              * Bean 覆值 populateBean(beanName, mbd, instanceWrapper)
                * 覆值之前 InstantiationAwareBeanPostProcessor 後置處理器
                  postProcessAfterInstantiation() 方法執行
                * 覆值之前 InstantiationAwareBeanPostProcessor 後置處理器
                  postProcessPropertyValues() 方法執行  
                == 覆值前 ==
                * applyPropertyValues(beanName, mbd, bw, pvs) 利用反射透過 setter 覆值
              * Bean 初始化
                * 執行Aware介面方法: invokeAwareMethods(beanName, bean);執行 Aware 介面的方法
                  (BeanNameAware、BeanClassLoaderAware、BeanFactoryAware)
                * 初始化之前，執行後置處理器方法: applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName)
                  BeanPostProcessor.postProcessorsBeforeInitialization()
                * invokeInitMethods(beanName, wrappedBean, mbd);
                  * 是否是 InitializingBean 的介面，執行規定的初始化
                  * 是否自訂義初始化方法
                * 初始化之前，執行後置處理器方法: BeanPostProcessor.applyBeanPostProcessorsAfterInitialization
                  BeanPostProcessor.postProcessorsAfterInitialization()
              * 註冊 Bean 銷毀方法
            * 將建立 Bean 加入至容器中 singletonObjects
           IoC 容器內包含了許多的上下文Map、Bean Map及環境訊息的Map
        8. 所有 Bean 建立完成後會判斷是否為 SmartInitializingSingleton介面，若為是則執行 afterSingletonsInstantiated
12. finishRefresh();完成 BeanFactory初始化的工作，IoC 容器及建立完成
    * initLifecycleProcessor(); 初始化生命週期相關的後置處理器
      可透過實作 LifecycleProcessor 針對 IoC 容器生命週期加入一些動作
      onRefresh()、onClose()，沒有則會建立 DefaultLifecycleProcessor()
    * getLifecycleProcessor()onRefresh()取得前面定義的生命週期物件並呼叫
      onRefresh() 方法
    * publishEvent(new ContextRefreshedEvent(this)); 發送容器建立完成訊息
- 總結
1. Spring IoC 容器中會先儲存註冊的 Bean 物件
   1. xml 註冊 bean: <bean>
   2. 註解註冊 bean: @Service、@Component、@Bean、...
2. Spring 容器會在正確的時間建立 Bean 物件
   1. 使用到該 Bean 物件時，利用 getBean 建立並儲存至 IoC 容器中
   2. 統一建立剩下所有 Bean，finishBeanFactoryInitialization
3. 後置處理器
   1. 每個 Bean 建立完成都會使用後置處理器完成進階功能
      AutowiredAnnotationBeanPostProcessor: 處理自動注入  
      AnnotationAwareAspectJAutoProxyCreator: 處理自動生成代理
4. 事件驅動
   * ApplicationListener 事件監聽
   * ApplicationEventMulticaster 事件發送