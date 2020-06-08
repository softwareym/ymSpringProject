package ym.project.login.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;


/*
   롬복 라이브러리 사용
   @Data : @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode을 한꺼번에 설정해주는 어노테이션

    -@RequiredArgsConstructor : final이나 @NonNull인 필드 값만 파라미터로 받는 생성자 생성
    -@NoArgsConstructor : 파라미터가 없는 기본 생성자를 생성
    -@AllArgsConstructor : 모든 필드 값을 파라미터로 받는 생성자 생성
    -@ToString : 클래스명(필드1명=필드1값,필드2명=필드2값,...) 식으로 출력
    -@EqualsAndHashCode : equals, hashCode 메소드 자동 생성 / callSuper = true로 설정하면 부모 클래스 필드 값들도 동일한지 체크하며, callSuper = false로 설정(기본값)하면 자신 클래스의 필드 값들만 고려
*/

@Data
@NoArgsConstructor
@Alias("LoginVO")
public class LoginVO {

    private String id;
    private String pwd;
    private int idnum;
    private String username;

}
