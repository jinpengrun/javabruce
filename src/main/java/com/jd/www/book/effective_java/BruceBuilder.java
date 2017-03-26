package com.jd.www.book.effective_java;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/3 下午12:25</li>
 * <li>function:</li>
 * </ul>
 */
public class BruceBuilder {
    public  static class NutritionFacts{
        private final int serviceSize;
        private final int services;
        private final int flat;
        private final int sodium;


        private NutritionFacts(Builder builder){
            this.services = builder.services;
            this.flat = builder.flat;
            this.serviceSize = builder.serviceSize;
            this.sodium = builder.sodium;
        }
    public  static class Builder implements ZBuilder<NutritionFacts>{
            //required parameters
            private final int serviceSize;
            private final int services;

            //optional parameters
            private int flat = 0;
            private int sodium = 0 ;

            public Builder(int serviceSize , int services){
                this.serviceSize = serviceSize;
                this.services = services;
            }

        @Override
        public String toString() {
            return "Builder{" +
                    "serviceSize=" + serviceSize +
                    ", services=" + services +
                    ", flat=" + flat +
                    ", sodium=" + sodium +
                    '}';
        }

        public Builder flat(int val){
                this.flat = val;
                return this;
            }

            public Builder sodium(int sodium){
                this.sodium = sodium;return this;
            }

            public NutritionFacts builder(){
                return new NutritionFacts(this);
            }
        }




    }

    //带泛型的builder

    public interface ZBuilder<T>{
        public T builder();
    }
    public static void main(String[] args) {
        BruceBuilder.NutritionFacts nutritionFacts =new BruceBuilder.NutritionFacts.Builder(1,1).flat(10).sodium(12).builder();
        System.out.println(nutritionFacts);
    }
}
