package wd.weselnedetale.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
	"wd.weselnedetale.controller",
	"wd.weselnedetale.database.dao",
	"wd.weselnedetale.database.dao.common",
	"wd.weselnedetale.model",
	"wd.weselnedetale.database.utils"
	})
public class SpringConfiguration {
 
}
