#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* 서버는 사용자 요청을 받기 직전까지, 초기화 과정을 진행하고 준비 상태에 있다가 사용자가 요청을 보내는 순간 응답을 한다.
* 서블릿 컨테이너는 시작과 동시에 사용자의 개입없이 초기화 과정을 진행한다.
* @WebListener 어노테이션으로 인해, ContextLoaderListener 파일이 실행이 된다.
* ContextLoaderListener 파일 안에 있는 contextInitialized 메서드를 실행하고, jwp.sql 파일에 있는 형식대로 populator 에 스크립트를 추가시킨다.
* DispatcherServlet 에서 init 메서드가 실행된다.
* init 메서드 안 - RequestMapping 객체를 생성한 후, initMapping 을 통해서 url 과 Controller 를 각각 매핑시킨다.
* 각 Controller 의 ModelAndView 가 실행되면, 사용자가 입력한 정보를 받아와서 DB 에 insert 한다.


#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
### 사용자의 요청이 왔을 때, 서버가 하는 일
1. service 메서드 안 - DispatcherServlet 에서 Client 보낸 요청 주소를 받는다. (getRequestURI() 사용)
2. RequestMapping Class 의 findController 메서드가 Client 요청한 주소에 대한 응답을 처리해줄 Controller Object 를 찾아서 Controller 변수에 저장한다.
3. controller.execute 메서드가 실행된다.
	- mav = controller.execute(req, resp);
	- mav 에는 요청에 대한 model 과 view 가 담긴 객체가 저장된다.
4. 이후 View 인터페이스를 구현한 Class 들의 render 메서드들이 실행되면, 미리 생성되었던 View 객체의 필드에 생성자에 의해 초기화 되어있던 viewName 으로 forward 하게 됨으로써 질문 목록이 보이게 된다.


#### 7. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 스레드 : 프로그램 내에서, 실행되는 흐름의 단위.
* 일반적으로 한 프로그램은 하나의 스레드를 가지지만, 둘 이상의 스레드를 동시에 실행시킬 수도 있다. -> 멀티쓰레드

