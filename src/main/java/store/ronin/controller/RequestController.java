package store.ronin.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import store.ronin.HttpClientUtil;

@Controller
public class RequestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
  @Value("${neteaseCloudMusicApi.Server.address}")
  private String neteaseCloudMusicApiServerAddress;
    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public ResponseEntity<Object> requestFilter(HttpServletRequest request) {
	String uri = request.getRequestURI();
	logger.debug("-------------request URL:" + uri);
	String json = HttpClientUtil.httpGet(neteaseCloudMusicApiServerAddress+uri);
	return new ResponseEntity<Object>(json, HttpStatus.OK);
    }

}
