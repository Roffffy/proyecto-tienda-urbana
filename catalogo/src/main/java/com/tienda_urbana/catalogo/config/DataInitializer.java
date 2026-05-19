package com.tienda_urbana.catalogo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tienda_urbana.catalogo.model.Categoria;
import com.tienda_urbana.catalogo.model.Producto;
import com.tienda_urbana.catalogo.repository.CategoriaRepository;
import com.tienda_urbana.catalogo.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoriaRepository catRepo;
    private final ProductoRepository prodRepo;

    @Override
    public void run(String... args){


        if (catRepo.count()>0) {
            return;
        }

        Categoria sinCategoria = catRepo.save(new Categoria(null, "Sin Categoria"));
        Categoria categoriaPoleras = catRepo.save(new Categoria(null, "Poleras"));
        Categoria categoriaPantalones = catRepo.save(new Categoria(null, "Pantalones"));
        Categoria categoriaChaquetas = catRepo.save(new Categoria(null, "Chaquetas"));
        Categoria categoriaPolerones = catRepo.save(new Categoria(null, "Polerones"));
        Categoria categoriaShorts = catRepo.save(new Categoria(null, "Shorts"));
        Categoria categoriaBuzos = catRepo.save(new Categoria(null, "Buzos"));
        Categoria categoriaCamisas = catRepo.save(new Categoria(null, "Camisas"));
        Categoria categoriaHombre = catRepo.save(new Categoria(null, "Hombre"));
        Categoria categoriaMujer = catRepo.save(new Categoria(null, "Mujer"));
        Categoria categoriaNiños= catRepo.save(new Categoria(null, "Niños"));

        if (prodRepo.count()>0) {
            return;
        }

        prodRepo.save(new Producto(null, "Polera básica negra", "100% algodón, ideal para el uso diario y combinar fácilmente", 14990, "M", 30, categoriaPoleras));
        prodRepo.save(new Producto(null, "Polera estampada rock", "Diseño gráfico vintage en el pecho con calce regular", 18990, "L", 15, categoriaPoleras));
        prodRepo.save(new Producto(null, "Polera oversize blanca", "Estilo urbano con hombros caídos y tela de alto gramaje", 16990, "S", 22, categoriaPoleras));

        prodRepo.save(new Producto(null, "Jeans slim fit azul", "Mezclilla elástica que se adapta al cuerpo, lavado oscuro", 34990, "42", 12, categoriaPantalones));
        prodRepo.save(new Producto(null, "Pantalón cargo verde oliva", "Con bolsillos laterales funcionales y puño ajustable en tobillos", 39990, "44", 8, categoriaPantalones));
        prodRepo.save(new Producto(null, "Pantalón de tela beige", "Corte chino elegante, perfecto para la oficina o eventos casuales", 29990, "40", 20, categoriaPantalones));

        prodRepo.save(new Producto(null, "Chaqueta de mezclilla clásica", "Botones metálicos y bolsillos frontales, un infaltable para el frío ligero", 45990, "L", 10, categoriaChaquetas));
        prodRepo.save(new Producto(null, "Chaqueta de cuero sintético", "Cierre cruzado estilo motoquero con forro interior suave", 59990, "M", 5, categoriaChaquetas));
        prodRepo.save(new Producto(null, "Parka impermeable negra", "Acolchada con chiporro interior y gorro desmontable para la lluvia", 69990, "XL", 14, categoriaChaquetas));

        prodRepo.save(new Producto(null, "Polerón con capucha gris", "Tela franela abrigadora con bolsillo canguro frontal", 24990, "M", 25, categoriaPolerones));
        prodRepo.save(new Producto(null, "Polerón cerrado azul marino", "Cuello redondo clásico con bordado sutil en el pecho", 22990, "L", 18, categoriaPolerones));
        prodRepo.save(new Producto(null, "Polerón con cierre oversize", "Estilo holgado con cierre completo y bolsillos laterales", 27990, "S", 12, categoriaPolerones));

        prodRepo.save(new Producto(null, "Short de mezclilla gastado", "Estilo clásico veraniego con bordes deshilachados", 19990, "38", 15, categoriaShorts));
        prodRepo.save(new Producto(null, "Short deportivo transpirable", "Tela ligera de secado rápido, ideal para running o gimnasio", 14990, "M", 40, categoriaShorts));
        prodRepo.save(new Producto(null, "Short chino color khaki", "Calce regular de algodón, combina excelente con camisas cortas", 21990, "42", 11, categoriaShorts));

        prodRepo.save(new Producto(null, "Pantalón de buzo algodón negro", "Cintura elástica con cordón y bolsillos con cierre para mayor seguridad", 24990, "L", 30, categoriaBuzos));
        prodRepo.save(new Producto(null, "Polerón de buzo deportivo", "Material elástico que facilita el movimiento durante el entrenamiento", 29990, "M", 20, categoriaBuzos));
        prodRepo.save(new Producto(null, "Conjunto de buzo completo gris", "Incluye chaqueta con cierre y pantalón a juego en tela micropolar", 49990, "XL", 7, categoriaBuzos));

        prodRepo.save(new Producto(null, "Camisa formal blanca manga larga", "Tela popelina de fácil planchado, cuello rígido para corbata", 29990, "M", 15, categoriaCamisas));
        prodRepo.save(new Producto(null, "Camisa de lino celeste verano", "Muy fresca y ligera, ideal para días calurosos junto a la playa", 32990, "L", 10, categoriaCamisas));
        prodRepo.save(new Producto(null, "Camisa leñadora a cuadros", "Franela suave de grosor medio, perfecta para usar abierta sobre una polera", 27990, "S", 14, categoriaCamisas));

        prodRepo.save(new Producto(null, "Abrigo largo de lana gris", "Corte elegante con botones frontales, ideal para invierno profundo", 89990, "L", 6, categoriaHombre));
        prodRepo.save(new Producto(null, "Suéter tejido cuello de tortuga", "Tejido suave de punto fino, calce ajustado para un look casual", 24990, "M", 15, categoriaHombre));
        prodRepo.save(new Producto(null, "Pantalón de cotelé marrón", "Estilo clásico retro de calce recto, tela abrigadora y resistente", 32990, "42", 10, categoriaHombre));

        prodRepo.save(new Producto(null, "Vestido largo floreado", "Corte recto con tirantes ajustables y caída ligera de primavera", 39990, "S", 12, categoriaMujer));
        prodRepo.save(new Producto(null, "Falda midi plisada negra", "Cintura elástica con caída fluida, combinable para toda temporada", 22990, "M", 14, categoriaMujer));
        prodRepo.save(new Producto(null, "Cardigan largo tejido beige", "Abierto sin botones, ideal para usar en capas los días frescos", 27990, "M", 18, categoriaMujer));

        prodRepo.save(new Producto(null, "Polera infantil dibujo animado", "Estampado frontal de alta resistencia a los lavados en algodón suave", 9990, "6", 35, categoriaNiños));
        prodRepo.save(new Producto(null, "Pantalón con elástico reforzado", "Rodilleras reforzadas antidesgaste para resistir todos los juegos", 15990, "8", 22, categoriaNiños));
        prodRepo.save(new Producto(null, "Pijama térmico de dinosaurios", "Dos piezas de plush extra suave para mantener el calor en invierno", 18990, "4", 18, categoriaNiños));
    }
}
