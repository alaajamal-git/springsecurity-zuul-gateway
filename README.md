# springsecurity-zuul-gateway

In this project, we configured Spring Security on Zuul Gateway to allow authenticated users to send specific requests to our spring services.
Not all of request should be authorized i.e. signup post request and login post request should bypass authentication filter, but all other requests should be authentication.
The authorization process in this project depends on JWT. if this token is found in http request and it is valid then the request be accepted otherwise it would be rejected.

Configuration steps :
      1. Create websecurity configuration class that extends WebSecurityConfigurerAdapter and overrides configure methods to setup httpsecurity and register our filter that will be triggered on every http request.
      2. Create our filter that extends BasicAuthenticationFilter and override doFilterInternal which would be called by the springframework to validate the sent token.

run example
1. sinup:
post   :  http://localhost:8011/users-ws/users
body   :  
<UserRest>
    <firatname>Ziad</firatname>
    <lastname>Jamal</lastname>
    <email>alaajamal470@gmail.com</email>
    <password>154454588</password>
</UserRest>

2. signin:
post  :  http://localhost:8011/users-ws/users/login
body  :  
{
    "email":"alaajamal470@gmail.com",
    "password":"154454588"
}

3. the respose of login request would have a token in the header. copy it : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMDBkYzFkNC05MjQ2LTQ5ZjItOWVjNC03MDAyMThmNzMwMGUiLCJleHAiOjE1OTU5NjYwODR9.T-nzY5QhzgmYIPi2Jx_nVAiFtUr2NW7ttcNzgn-4fxA
GET  :  http://localhost:8011/users-ws/users/status/check
Header : Key= Authorization
	 value= Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMDBkYzFkNC05MjQ2LTQ5ZjItOWVjNC03MDAyMThmNzMwMGUiLCJleHAiOjE1OTU5NjYwODR9.T-nzY5QhzgmYIPi2Jx_nVAiFtUr2NW7ttcNzgn-4fxA

