# springsecurity-zuul-gateway

In this project, we configured Spring Security on Zuul Gateway to allow authenticated users to send specific requests to our spring services.
Not all of request should be authorized i.e. signup post request and login post request should bypass authentication filter, but all other requests should be authentication.
The authorization process in this project depends on JWT. if this token is found in http request and it is valid then the request be accepted otherwise it would be rejected.

steps :
      1. Create websecurity configuration class that extends WebSecurityConfigurerAdapter and overrides configure methods to setup httpsecurity and register our filter that will be triggered on every http request.
	    2. Create our filter that extends BasicAuthenticationFilter and override doFilterInternal which would be called by the springframework to validate the sent token.
