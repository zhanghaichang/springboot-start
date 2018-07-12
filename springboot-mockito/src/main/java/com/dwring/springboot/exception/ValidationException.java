/**
 * @Title: [ValidationException.java]
 * @Package: [com.qf.loan.exception]
 * @author: [YanweiQin]
 * @CreateDate: [2017/3/28 22:05]
 * @UpdateUser: [YanweiQin]
 * @UpdateDate: [2017/3/28 22:05]
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [参数异常，不被客户端解析]
 * @version: [V1.0]
 */
package com.dwring.springboot.exception;



/**  
* @ClassName: ValidationException  
* @Description:  
* @author haichangzhang  
* @date 2018年6月10日 下午2:35:34  
*    
*/
public class ValidationException extends BizException {
    private static final long serialVersionUID = -5609351150726850186L;


    public ValidationException(String message){
        super(message);
    }
    
}
