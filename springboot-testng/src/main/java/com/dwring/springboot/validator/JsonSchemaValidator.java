/**
 * @Title: [JsonSchemaValidator.java]
 * @Package: [com.qf.loan.validate]
 * @author: [YanweiQin]
 * @CreateDate: [2017/3/30 20:24]
 * @UpdateUser: [YanweiQin]
 * @UpdateDate: [2017/3/30 20:24]
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [JsonSchema验证]
 * @version: [V1.0]
 */
package com.dwring.springboot.validator;

import com.dwring.springboot.exception.ValidationException;
import com.dwring.springboot.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Iterator;
import java.util.Map;

/**  
* @ClassName: JsonSchemaValidator  
* @Description:  
* @author haichangzhang  
* @date 2018年6月10日 下午2:34:46  
*    
*/
@Component
@Slf4j
public class JsonSchemaValidator {

	/**
	 * 校验map
	 *
	 * @return
	 */
	public String validatorSchema(String mainSchema, Map<String,Object> map) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			ObjectNode node = (ObjectNode) mapper.readTree(JsonUtils.beanToJson(map));
			return validatorSchema(mainSchema, node);
		} catch (Exception e) {
			log.error("JsonSchema parsed map exception", e);
			throw new ValidationException("Json validation failed");
		}
	}

	/**
	 * 校验JSON
	 *
	 * @return
	 */
	public String validatorSchema(String mainSchema, ObjectNode node) {
		ProcessingReport report;
		StringBuilder errorMsg = new StringBuilder();
		try {
			JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
			JsonSchema jsonSchema = jsonSchemaFactory.getJsonSchema("resource:/templates/"+mainSchema);
			report = jsonSchema.validate(node);
			// 错误信息显示
			Iterator<ProcessingMessage> processingMessageIterator = report.iterator();
			while (processingMessageIterator.hasNext()) {
				errorMsg.append(processingMessageIterator.next().getMessage());
			}

		} catch (ProcessingException e) {
			log.error("JsonSchema parsed exception", e);
			throw new ValidationException("Json validation failed");
		}
		if (!report.isSuccess()) {
			log.info("================Verify the jsonSchema error message: {}", errorMsg.toString());

		}
		return errorMsg.toString();
	}

}
