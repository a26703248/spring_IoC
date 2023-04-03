<h1 id="spring_ioc">spring_IoC</h1>
<h3 id="spring-bean-refresh-">Spring Bean refresh 流程</h3>
<p><img src=".\src\main\resources\images\Image 1.png" alt="Alt refresh"></p>
<ol>
<li>prepareRefresh();刷新前的預先處理
<img src=".\src\main\resources\images\Image 2.png" alt="Alt prepareRefresh"><ul>
<li>getEnvironment初始化屬性設定;子類自訂義個人屬性設置方法</li>
<li>earlyApplicationEvent().validateRequiredProperties();驗證屬性合法性</li>
<li>earlyApplicationEvents = new LinkHashSet&lt;ApplicationEvent&gt;();保存初期事件</li>
</ul>
</li>
<li>obtainFreshBeanFactory();獲得BeanFactory
<img src=".\src\main\resources\images\Image 4.png" alt="Alt obtainFreshBeanFactory"><ul>
<li>refreshBeanFactory();刷新建立BeanFactory</li>
<li>建立 this.beanFactory = new DefaultListableBeanFactory() ;設定Id</li>
<li>getBeanFactory();返回 GenericApplicationContext 建立的 BeanFactory 物件</li>
<li>將建立的 BeanFactory(DefaultListableBeanFactory) 返回,</li>
</ul>
</li>
<li>prepareBeanFactory(beanFactory);BeanFactory 的準備工作(BeanFactory 準備工作)
<img src=".\src\main\resources\images\Image 5.png" alt="Alt prepareBeanFactory"><ul>
<li>設定 BeanFactory 的 ClassLoader、表達式解析器</li>
<li>添加部分 BeanPostProcessor(ApplicationContextAwareProcessor)</li>
<li>設定忽略自動裝配的介面 EnvironmentAware、EmbeddedValueResolverAware、...等</li>
<li>註冊可解析的自動裝配，讓物件能夠自動注入，BeanFactory、ResourceLoader
、ApplicationEventPublisherAware、MessageSourceAware、ApplicationContextAware
、ApplicationStartupAware</li>
<li>添加 BeanPostProcessor(ApplicationListenerDetector) ... 等</li>
<li>添加編譯時的 AspectJ</li>
<li>BeanFactory 註冊一些環境物件environment、systemProperties、systemEnvironment、applicationStartup</li>
</ul>
</li>
<li>postProcessBeanFactory(beanFactory);BeanFactory 準備工作完成的後置器處理
<img src=".\src\main\resources\images\Image 6.png" alt="Alt postProcessBeanFactory"><ul>
<li>子類可同過複寫 postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) 方法增加一些準備工作</li>
</ul>
</li>
<li>invokeBeanFactoryPostProcessors(beanFactory);執行所有繼承 BeanPostProcessor 子類
<img src=".\src\main\resources\images\Image 7.png" alt="Alt invokeBeanFactoryPostProcessors"><ul>
<li>BeanPostProcessor: BeanFactory 後置處理器，在 BeanFactory 準備工作完成後執行兩個介面
BeanDefinitionRegistryPostProcessor、BeanPostProcessor
<img src=".\src\main\resources\images\Image 9.png" alt="Alt invokeBeanFactoryPostProcessors"></li>
<li>執行 BeanPostProcessor 的方法<ul>
<li>獲得所有 BeanDefinitionRegistryPostProcessor</li>
<li>優先執行時做 PriorityOrdered 介面的 BeanDefinitionRegistryPostProcessor、
postProcessor、postProcessBeanDefinitionRegistry(registry)</li>
<li>在執行時做 Ordered 順序的介面 BeanDefinitionRegistryPostProcessor、
postProcessor、postProcessBeanDefinitionRegistry(registry)</li>
<li>最後執行未實做任何順序的 BeanDefinitionRegistryPostProcessor、
postProcessor、postProcessBeanDefinitionRegistry(registry)</li>
</ul>
</li>
<li>在執行 BeanFactoryPostProcessor 的方法<ul>
<li>獲得所有 BeanFactoryPostProcessor</li>
<li>優先執行時做 PriorityOrdered 介面的 BeanDefinitionRegistryPostProcessor、
postProcessor、postProcessBeanDefinitionRegistry(registry)</li>
<li>在執行時做 Ordered 順序的介面 BeanDefinitionRegistryPostProcessor、
postProcessor、postProcessBeanDefinitionRegistry(registry)</li>
<li>最後執行未實做任何順序的 BeanDefinitionRegistryPostProcessor、
postProcessor、postProcessBeanDefinitionRegistry(registry)</li>
</ul>
</li>
</ul>
</li>
<li>registerBeanPostProcessors(beanFactory);註冊 BeanPostProcessor(Bean的後置處理器)，攔截 Bean 建立的過程
 <img src=".\src\main\resources\images\image 10.png" alt="alt registerBeanPostProcessors"><ul>
<li>不同類型的 BeanPostProcessor，在 Bean 建立前後的執行時間也不同<br>BeanPostProcessor<br>InstantiationAwareBeanPostProcessor<br>DestructionAwareBeanPostProcessor<br>SmartInstantiationAwareBeanPostProcessor<br>MergedBeanDefinitionPostProcessor<ul>
<li>獲得所有的 BeanPostProcessor;BeanPostProcessor 也支持 Ordered、PriorityOrdered 排序</li>
<li>先註冊 PriorityOrdered 的 BeanPostProcessor，把每個 BeanPostProcessor，添加到 
BeanFactory 中</li>
<li>在註冊 Ordered 介面 BeanPostProcessor</li>
<li>在註冊執行未實做任何順序的 BeanPostProcessor</li>
<li>在註冊 MergedBeanDefinitionPostProcessor</li>
<li>最終註冊 ApplicationListenerDetector，來在 Bean 檢查是否為 ApplicationListenerDetector<ul>
<li>若為是則儲存置 ApplicationListenerList 中</li>
</ul>
</li>
</ul>
</li>
</ul>
</li>
<li>initMessageSource();初始化MessageSource物件(國際化、消息綁定、消息解析)
<img src=".\src\main\resources\images\image 11.png" alt="alt registerBeanPostProcessors"><ul>
<li>獲得 BeanFactory</li>
<li>尋找容器中是否有 MessageSource 覆值給 messageSource，沒有則會建立 DelegatingMessageSource
取得國際化設定文件中的 key值，能按區域訊息取得</li>
<li>把建立好的 MessageSource 註冊製容器中，後續獲得國際化設定檔的時候，可自動注入 MessageSource</li>
</ul>
</li>
<li>initApplicationEventMulticaster();初始化事件發送器
<img src=".\src\main\resources\images\image 12.png" alt="alt registerBeanPostProcessors"><ul>
<li>獲得所有的 BeanPostProcessor</li>
<li>從 BeanFactory 尋找 applicationEventMulticaster 的 ApplicationEventMulticaster</li>
<li>沒有則建立 SimpleApplicationEventMulticaster</li>
<li>將 applicationEventMulticaster 物件註冊至容器中</li>
</ul>
</li>
<li>onRefresh();
<img src=".\src\main\resources\images\image 13.png" alt="alt registerBeanPostProcessors"><ul>
<li>保留給子類別複寫使用，在容器更新的時候自訂義邏輯</li>
</ul>
</li>
<li>registerListeners(); 註冊所有監聽器
<img src=".\src\main\resources\images\image 14.png" alt="alt registerBeanPostProcessors"><ul>
<li>取得所有 ApplicationListener</li>
<li>將每個監停器註冊到 ApplicationEventMulticaster 中</li>
<li>發送之前發生的動作</li>
</ul>
</li>
<li>finishBeanFactoryInitialization(beanFactory);初始化所有剩下的 Bean
<img src=".\src\main\resources\images\image 15.png" alt="alt registerBeanPostProcessors"><ul>
<li>獲得容器中的所有 Bean，依序進行初始化和建立物件</li>
<li>獲得 Bean 的定義訊息，RootBeanDefinition</li>
<li>判斷 Bean 是否為抽象、單例、懶加載<ul>
<li>判斷是否為 FactoryBean，是則使用工廠模式建立，不是則使用 getBean() 建立</li>
<li>建立物件<ol>
<li>getBeam()</li>
<li>doGetBean(name, null, null, false)</li>
<li>先尋找容器是否已被建立過</li>
<li>若搜尋不到則開始調用 FactoryBean 建立</li>
<li>標記當前 Bean 已被建立(方只多執行緒)</li>
<li>取得當前 Bean 所依賴的 Bean並將其建立</li>
<li>使用單例 FactoryBean 建立 Bean 物件<ul>
<li>createBean(beanName, mdb, args)</li>
<li>Object bean = resolveBeforeInstantiation() 讓 BeanPostProcessor 攔截 Bean 建立流程<br>InstantiationAwareBeanPostProcessor 提前執行呼叫 postProcessBeforeInstantiation 方法<br>如果有返回值則呼叫 postProcessAfterInitialization() 方法</li>
<li>如果前面的 InstantiationAwareBeanPostProcessor 沒返回代理物件則會 doCreateBean(beanName, mdb, args) 建立<ul>
<li>doCreateBean 內部呼叫 getBeanInstance(beanName, mdb, args) 利用工廠模式建立 Bean</li>
<li>applyMergeBeanDefinitionPostProcessor(mbd, beanType, beanName)，會呼叫 MergedBeanDefinitionPostProcessors 中
postProcessMergedBeanDefinition 方法</li>
<li>Bean 覆值 populateBean(beanName, mbd, instanceWrapper)<ul>
<li>覆值之前 InstantiationAwareBeanPostProcessor 後置處理器
postProcessAfterInstantiation() 方法執行</li>
<li>覆值之前 InstantiationAwareBeanPostProcessor 後置處理器
postProcessPropertyValues() 方法執行<br>== 覆值前 ==</li>
<li>applyPropertyValues(beanName, mbd, bw, pvs) 利用反射透過 setter 覆值</li>
</ul>
</li>
<li>Bean 初始化<ul>
<li>執行Aware介面方法: invokeAwareMethods(beanName, bean);執行 Aware 介面的方法
(BeanNameAware、BeanClassLoaderAware、BeanFactoryAware)</li>
<li>初始化之前，執行後置處理器方法: applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName)
BeanPostProcessor.postProcessorsBeforeInitialization()</li>
<li>invokeInitMethods(beanName, wrappedBean, mbd);<ul>
<li>是否是 InitializingBean 的介面，執行規定的初始化</li>
<li>是否自訂義初始化方法</li>
</ul>
</li>
<li>初始化之前，執行後置處理器方法: BeanPostProcessor.applyBeanPostProcessorsAfterInitialization
BeanPostProcessor.postProcessorsAfterInitialization()</li>
</ul>
</li>
<li>註冊 Bean 銷毀方法</li>
</ul>
</li>
<li>將建立 Bean 加入至容器中 singletonObjects
IoC 容器內包含了許多的上下文Map、Bean Map及環境訊息的Map</li>
</ul>
</li>
<li>所有 Bean 建立完成後會判斷是否為 SmartInitializingSingleton介面，若為是則執行 afterSingletonsInstantiated</li>
</ol>
</li>
</ul>
</li>
</ul>
</li>
<li>finishRefresh();完成 BeanFactory初始化的工作，IoC 容器及建立完成<ul>
<li>initLifecycleProcessor(); 初始化生命週期相關的後置處理器
可透過實作 LifecycleProcessor 針對 IoC 容器生命週期加入一些動作
onRefresh()、onClose()，沒有則會建立 DefaultLifecycleProcessor()</li>
<li>getLifecycleProcessor()onRefresh()取得前面定義的生命週期物件並呼叫
onRefresh() 方法</li>
<li>publishEvent(new ContextRefreshedEvent(this)); 發送容器建立完成訊息</li>
</ul>
</li>
<li>總結</li>
<li>Spring IoC 容器中會先儲存註冊的 Bean 物件<ol>
<li>xml 註冊 bean: <bean></li>
<li>註解註冊 bean: @Service、@Component、@Bean、...</li>
</ol>
</li>
<li>Spring 容器會在正確的時間建立 Bean 物件<ol>
<li>使用到該 Bean 物件時，利用 getBean 建立並儲存至 IoC 容器中</li>
<li>統一建立剩下所有 Bean，finishBeanFactoryInitialization</li>
</ol>
</li>
<li>後置處理器<ol>
<li>每個 Bean 建立完成都會使用後置處理器完成進階功能
AutowiredAnnotationBeanPostProcessor: 處理自動注入<br>AnnotationAwareAspectJAutoProxyCreator: 處理自動生成代理</li>
</ol>
</li>
<li>事件驅動<ul>
<li>ApplicationListener 事件監聽</li>
<li>ApplicationEventMulticaster 事件發送</li>
</ul>
</li>
</ol>
