package Models;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-01T02:39:01", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Comandas.class)
public class Comandas_ { 

    public static volatile SingularAttribute<Comandas, Integer> idComanda;
    public static volatile SingularAttribute<Comandas, Integer> codigo;
    public static volatile SingularAttribute<Comandas, String> nomeCliente;
    public static volatile SingularAttribute<Comandas, String> status;

}