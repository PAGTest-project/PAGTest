
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Ast;
import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.ast.Op;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Resolver_toCoreTest {

    @Test
    void testToCoreValDecl() {
        Resolver resolver = new Resolver(null, null, null, null, null);
        Ast.ValDecl valDecl = new Ast.ValDecl();
        valDecl.op = Op.VAL_DECL;

        Core.Decl result = resolver.toCore(valDecl);

        assertNotNull(result);
        assertTrue(result instanceof Core.ValDecl);
    }

    @Test
    void testToCoreDatatypeDecl() {
        Resolver resolver = new Resolver(null, null, null, null, null);
        Ast.DatatypeDecl datatypeDecl = new Ast.DatatypeDecl();
        datatypeDecl.op = Op.DATATYPE_DECL;

        Core.Decl result = resolver.toCore(datatypeDecl);

        assertNotNull(result);
        assertTrue(result instanceof Core.DatatypeDecl);
    }

    @Test
    void testToCoreUnknownDecl() {
        Resolver resolver = new Resolver(null, null, null, null, null);
        Ast.Decl unknownDecl = new Ast.Decl() {
            @Override
            public Op getOp() {
                return Op.UNKNOWN;
            }
        };

        assertThrows(AssertionError.class, () -> resolver.toCore(unknownDecl));
    }
}
